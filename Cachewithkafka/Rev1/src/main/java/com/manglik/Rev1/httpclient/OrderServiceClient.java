package com.manglik.Rev1.httpclient;


import com.manglik.Rev1.DTO.Order;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class OrderServiceClient {
    private RestTemplate restTemplate = new RestTemplateBuilder().setConnectTimeout(Duration.of(20L, ChronoUnit.SECONDS))
            .setReadTimeout(Duration.of(20L, ChronoUnit.SECONDS)).build();


    public List<Order> getOrder(int personId) {
        UriComponentsBuilder path = UriComponentsBuilder.fromHttpUrl("http://localhost:8081/order/pid/").path(String.valueOf(personId));
        ResponseEntity<List> orderResponseEntity =  restTemplate.getForEntity(path.toUriString(), List.class);
        return orderResponseEntity.getBody();
    }

}
