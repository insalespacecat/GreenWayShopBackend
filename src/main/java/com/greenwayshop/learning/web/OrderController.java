package com.greenwayshop.learning.web;


import com.greenwayshop.learning.api.OrderRepository;
import com.greenwayshop.learning.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/order")
@Slf4j
@CrossOrigin
public class OrderController {

    private OrderRepository orderRepository;
    OrderController(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @PostMapping(consumes = "Application/JSON")
    public void placeOrder(@RequestBody Order order){
        log.info("order: " + order.toString());
        orderRepository.save(order);
    }
    @GetMapping(produces = "Application/JSON")
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
