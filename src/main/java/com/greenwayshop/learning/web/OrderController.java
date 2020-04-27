package com.greenwayshop.learning.web;


import com.greenwayshop.learning.api.OrderRepository;
import com.greenwayshop.learning.domain.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    private OrderRepository orderRepository;
    OrderController(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @PostMapping
    public void placeOrder(@RequestBody Order order){
        log.info("order: " + order.toString());
        orderRepository.save(order);
    }
    @GetMapping
    public ArrayList<Order> getAllOrders(){
        return orderRepository.findAll();
    }
    @GetMapping("/last")
    public Order getLastOrder(){
        Order order = orderRepository.findTopByOrderByIdDesc();
        log.info(order.toString());
        return order;
    }
}
