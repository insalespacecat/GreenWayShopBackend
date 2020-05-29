package com.greenwayshop.learning.services;

import com.greenwayshop.learning.api.OrderRepository;
import com.greenwayshop.learning.domain.Order;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import static com.greenwayshop.learning.services.CheckMethods.checkForEmptyAndThrowResponseTypeExcIfNeeded;
import static com.greenwayshop.learning.services.CheckMethods.checkForNullAndTrowResponseTypeExcIfNeeded;

@Service
@AllArgsConstructor
public class OrderService {

    private OrderRepository orderRepository;
    private UserService userService;
    private DiscountService discountService;

    public void placeOrder(Order order) {
        checkForNullAndTrowResponseTypeExcIfNeeded(order);
        orderRepository.save(order);
        discountService.patchUserDiscountByOrdersTotal(order);
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public List<Order> getAllOrdersByUsername(String username) {
        checkForNullAndTrowResponseTypeExcIfNeeded(username);
        Optional<List<Order>> optListOfOrder = orderRepository.findAllByUser(
                userService.getUserInfoByUsername(username)
        );
        checkForEmptyAndThrowResponseTypeExcIfNeeded(optListOfOrder);
        return optListOfOrder.get();
    }

    public Order getTopOrderByIdDesc(){
        return orderRepository.findTopByOrderByIdDesc();
    }

}