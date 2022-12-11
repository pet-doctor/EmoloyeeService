package com.petdoctor.employee.kafka;

import java.util.Map;

public interface KafkaService {

    void sendMessage(Object payload, String topic);
}
