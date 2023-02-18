package com.petdoctor.employee.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;
import java.io.InputStream;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static com.fasterxml.jackson.databind.DeserializationFeature.*;
import static com.fasterxml.jackson.databind.MapperFeature.ALLOW_COERCION_OF_SCALARS;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

/**
 * Утилитный класс по работе с тестами и ресурсами.
 */
@Slf4j
@UtilityClass
public class TestsUtils {

    public static ObjectMapper TEST_OBJ_MAPPER;

    static {
        TEST_OBJ_MAPPER = new Jackson2ObjectMapperBuilder()
                .serializationInclusion(NON_EMPTY)
                .failOnEmptyBeans(false)
                .deserializerByType(String.class, new StringDeserializer())
                .featuresToDisable(
                        FAIL_ON_IGNORED_PROPERTIES,
                        FAIL_ON_INVALID_SUBTYPE,
                        WRITE_DATES_AS_TIMESTAMPS,
                        ACCEPT_FLOAT_AS_INT,
                        ALLOW_COERCION_OF_SCALARS
                )
                .featuresToEnable(ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, ADJUST_DATES_TO_CONTEXT_TIME_ZONE)
                .modulesToInstall(JavaTimeModule.class)
                .build();
    }

    /**
     * Метод получения объекта указанного класса из файла *.json.
     *
     * @param path  Адрес файла.
     * @param clazz Целевой класс.
     * @param <T>   Целевой класс, как тип.
     * @return Объект указанного класса.
     */
    public <T> T getFromFile(String path, Class<T> clazz) {

        T object = null;
        try (InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(path)) {
            object = TEST_OBJ_MAPPER.readValue(stream, clazz);
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
        }

        return object;
    }


    /**
     * Метод преобразования json в PrettyString.
     *
     * @param json json, приведённый к Object.
     * @return PrettyString.
     */
    public String getJsonAsString(Object json) {
        try {
            return TEST_OBJ_MAPPER.readTree(json.toString()).toPrettyString();
        } catch (JsonProcessingException ex) {
            log.error(ex.getMessage(), ex);
            return null;
        }
    }
}