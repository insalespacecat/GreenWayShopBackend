package com.greenwayshop.learning.services;

import com.greenwayshop.learning.api.OrderRepository;
import com.greenwayshop.learning.domain.Order;
import com.greenwayshop.learning.domain.User;
import com.greenwayshop.learning.properties.ServiceProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import static com.greenwayshop.learning.services.CheckMethods.checkForEmptyAndThrowResponseTypeExcIfRequired;
import static com.greenwayshop.learning.services.CheckMethods.checkForNullAndTrowResponseTypeExcIfRequired;

@Service
@AllArgsConstructor
@Slf4j
public class OrderService {

    private OrderRepository orderRepository;
    private UserService userService;
    private DiscountService discountService;
    private ServiceProperties serviceProperties;

    public void placeOrder(Order order) {
        checkForNullAndTrowResponseTypeExcIfRequired(order);
        orderRepository.save(order);
        //discountService.checkAndUpdateUserDiscountIfRequired(order);
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public List<Order> getAllOrdersByUsername(String username) {
        checkForNullAndTrowResponseTypeExcIfRequired(username);
        Optional<List<Order>> optListOfOrder = orderRepository.findAllByUser(
                userService.getUserInfoByUsername(username)
        );
        checkForEmptyAndThrowResponseTypeExcIfRequired(optListOfOrder);
        return optListOfOrder.get();
    }

    public Order getTopOrderByIdDesc(){
        return orderRepository.findTopByOrderByIdDesc();
    }

    public Page<Order> getTopSliceForOperatorView() {
        Pageable pageRequest = PageRequest.of(0, serviceProperties.getTopSliceForOperatorViewSize());
        return orderRepository.findAll(pageRequest);
    }

}
