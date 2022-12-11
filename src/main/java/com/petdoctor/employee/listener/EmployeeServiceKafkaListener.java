package com.petdoctor.employee.listener;

import com.petdoctor.employee.kafka.KafkaConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmployeeServiceKafkaListener {

    @KafkaListener(
            topics = KafkaConstant.KAFKA_EMPLOYEE_VETCLINIC_TOPIC,
            groupId = KafkaConstant.KAFKA_EMPLOYEE_VETCLINIC_GROUP)
    public void vetClinicListener(String message) {

        log.info("message delivered to vetclinic's consumer successfully");
        log.info(message);
    }

    @KafkaListener(
            topics = KafkaConstant.KAFKA_EMPLOYEE_DOCTOR_TOPIC,
            groupId = KafkaConstant.KAFKA_EMPLOYEE_DOCTOR_GROUP)
    public void doctorListener(String message) {

        log.info("message delivered to doctor's consumer successfully");
        log.info(message);
    }
}
