package com.greenwayshop.learning.services;

import com.greenwayshop.learning.api.OrderRepository;
import com.greenwayshop.learning.domain.Order;
import com.greenwayshop.learning.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OrderService {

    private OrderRepository orderRepository;
    OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrdersByUserId(Long UserId){
        Optional<List<Order>> optListOfOrder = Optional.ofNullable(orderRepository.findAllByUserId(UserId));
        if(optListOfOrder.isEmpty()){
            log.info("Attempt to retrieve orders by user id that does not exist!");
            return null;
        }
        return optListOfOrder.get();
    }
}
