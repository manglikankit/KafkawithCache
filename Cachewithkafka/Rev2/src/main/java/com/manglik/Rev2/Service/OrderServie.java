package com.manglik.Rev2.Service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.manglik.Rev2.DTO.Notification;
import com.manglik.Rev2.Entity.Order;
import com.manglik.Rev2.Repo.OrderRepo;
import com.manglik.Rev2.Rev2.OrderNotFoundException;
import com.manglik.Rev2.enums.NotificationType;
import com.manglik.Rev2.kafka.Producer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServie {


    private final OrderRepo orderRepo;
    private final Producer producer;
    private ObjectMapper mapper = new ObjectMapper();

    public Order saveOrder(Order order) throws JsonProcessingException {
        // send notificatiion payload to notification service
        producer.send("com.ankit", mapper.writeValueAsString(Notification.builder().data("Manglik").pid(String.valueOf(order.getPid())).notificationType(NotificationType.EMAIL).build()));
        return orderRepo.save(order);
    }
    public Order getOrder(int id){
        return orderRepo.findById(id).orElseThrow(() -> new OrderNotFoundException("order not found", new Throwable("not found")));
    }

    public List<Order> getOrdersByPid(int pid){
        return orderRepo.findByPid(pid);
    }
}
