package com.manglik.Rev1.kafka;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.manglik.Rev1.DTO.Payload;
import com.manglik.Rev1.Service.PersonServie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class Consumer {

@Autowired
    PersonServie personServie;

    @KafkaListener(topics = "com.ankit")
    public void consumer(String payload) {
        System.out.println(payload);
        ObjectMapper mapper = new ObjectMapper();
        try {
            Payload message = mapper.readValue(payload, Payload.class);
            System.out.println("Payload message to send "+ message );
//            send(message.getPid());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void send(String id){
//        personServie.sendMail(Integer.parseInt(id));
//    }
}
