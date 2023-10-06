package com.manglik.Rev2.kafka;


import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Producer {
    private final KafkaTemplate kafkaTemplate;
    public void send(String topic, String object) {
        kafkaTemplate.send(topic, object);
    }
}
