package com.manglik.Rev2.Controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.manglik.Rev2.Entity.Order;
import com.manglik.Rev2.Service.OrderServie;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderServie orderServie;

    @PostMapping
    public ResponseEntity<Order> saveOrder(@RequestBody @Valid Order order) throws JsonProcessingException {
        return new ResponseEntity<>(orderServie.saveOrder(order), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder( @PathVariable Integer id){
        return new ResponseEntity<>(orderServie.getOrder(id), HttpStatus.OK);
    }

    @GetMapping("/pid/{pid}")
    public ResponseEntity<List<Order>> getOrderByPid(@PathVariable Integer pid){
        return new ResponseEntity<>(orderServie.getOrdersByPid(pid), HttpStatus.OK);
    }
}
