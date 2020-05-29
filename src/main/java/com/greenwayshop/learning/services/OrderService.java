package com.greenwayshop.learning.services;

import com.greenwayshop.learning.api.OrderRepository;
import com.greenwayshop.learning.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static com.greenwayshop.learning.services.CheckMethods.checkForEmptyAndThrowResponseTypeExcIfNeeded;
import static com.greenwayshop.learning.services.CheckMethods.checkForNullAndTrowResponseTypeExcIfNeeded;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }
    //i know that ResponseStatusExc is RuntimeExc, just wanted to mark
    //throws explicitly
    public void placeOrder(Order order) {
        //order can be null cuz we are passing request body from the controller without any checking
        checkForNullAndTrowResponseTypeExcIfNeeded(order);
        orderRepository.save(order);
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public List<Order> getAllOrdersByUserId(Long UserId) {
        checkForNullAndTrowResponseTypeExcIfNeeded(UserId);
        Optional<List<Order>> optListOfOrder = orderRepository.findAllByUserId(UserId);
        checkForEmptyAndThrowResponseTypeExcIfNeeded(optListOfOrder);
        return optListOfOrder.get();
    }

    public Order getTopOrderByIdDesc(){
        return orderRepository.findTopByOrderByIdDesc();
    }

}
