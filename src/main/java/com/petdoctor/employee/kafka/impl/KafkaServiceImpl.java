package com.petdoctor.employee.kafka.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.petdoctor.employee.EmployeeServiceApp;
import com.petdoctor.employee.kafka.KafkaService;
import com.petdoctor.employee.tool.exception.EmployeeServiceSerializingException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
@Slf4j
public class KafkaServiceImpl implements KafkaService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void sendMessage(Object payload, String topic) {

        JsonNode rootNode = null;
        try {
            ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
            String payloadJson = ow.writeValueAsString(payload);
            rootNode = objectMapper.readTree(payloadJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        if (rootNode == null) {
            throw new EmployeeServiceSerializingException("Exception occurred due serializing the message");
        }
        log.info("current payload: " + rootNode);

        String message = rootNode.toString();

        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, message);

        kafkaTemplate.send(producerRecord);
    }
}
