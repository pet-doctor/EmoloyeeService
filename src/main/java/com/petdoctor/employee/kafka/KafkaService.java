package com.petdoctor.employee.kafka;

public interface KafkaService {

    void sendMessage(Object payload, String topic);
}
