package com.greenwayshop.learning.web;


import com.greenwayshop.learning.api.OrderRepository;
import com.greenwayshop.learning.domain.Order;
import com.greenwayshop.learning.services.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@Slf4j
@CrossOrigin
public class OrderController {

    private OrderRepository orderRepository;
    private OrderService orderService;
    OrderController(OrderRepository orderRepository, OrderService orderService){
        this.orderService = orderService;
        this.orderRepository = orderRepository;
    }

    @PostMapping(consumes = "Application/JSON")
    public void placeOrder(@RequestBody Order order){
        log.info("order: " + order.toString());
        orderRepository.save(order);
    }

    //TODO: Pagination here!
    @GetMapping(value = "/getAll", produces = "Application/JSON")
    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    //Maybe it should give back Page<T> but it does not quite fit
    //Angular Material tables and shop is small, which means that
    //we won't have a harmful situation here if we leave it this way.
    @GetMapping(value = "/getAllOrdersByUser/${userId}")
    public List<Order> getAllOrdersByUser(@PathVariable Long userId){
        return orderService.getAllOrdersByUserId(userId);
    }

    @GetMapping(value = "/last", produces = "Application/JSON")
    public Order getLastOrder(){
        Order order = orderRepository.findTopByOrderByIdDesc();
        log.info(order.toString());
        return order;
    }
}
