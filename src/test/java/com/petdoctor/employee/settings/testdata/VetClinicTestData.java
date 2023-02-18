package com.petdoctor.employee.settings.testdata;

import com.petdoctor.employee.model.entity.VetClinicEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class VetClinicTestData {

    @Getter
    @RequiredArgsConstructor
    public enum Request {

        I_CASE_DATA("Valid params for registration vet clinic",
                "json/vetclinic/1_case_data_vet_clinic.json"),
        II_CASE_DATA("Valid params for registration vet clinic without one of the required param",
                "json/vetclinic/2_case_data_vet_clinic.json"),
        III_CASE_DATA("Valid params for registration vet clinic, but vet clinic with the id already exist",
                "json/vetclinic/3_case_data_vet_clinic.json");

        private final String description;
        private final String value;

        @Override
        public String toString() {
            return description;
        }
    }

    @Getter
    @RequiredArgsConstructor
    public enum ExpectedData {

        I_EXPECTED_DATA(
                "Клиент, у которого изменены все поля",
                VetClinicEntity.builder()
                        .id(1L)
                        .email("vetclinic@yandex.ru")
                        .address("Kalatushkina")
                        .build()
        );

        private final String description;
        private final VetClinicEntity entity;

        @Override
        public String toString() {
            return description;
        }
    }
}

