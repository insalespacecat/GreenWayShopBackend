package com.greenwayshop.learning.services;

import com.greenwayshop.learning.domain.Order;
import com.greenwayshop.learning.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DiscountService {
    UserService userService;

    void patchUserDiscountByOrdersTotal(Order order) {
      User user = order.getUser();
      user.setOrdersTotal(user.getOrdersTotal() + order.getTotal());
      //TODO: Replace boilerplate discounts and totals with properties
      if(user.getOrdersTotal() > 500){
          user.setDiscount(10.0);
          userService.patchUserInfo(user, user.getUsername());
          return;
      }
      if(user.getOrdersTotal() > 200){
          user.setDiscount(7.0);
          userService.patchUserInfo(user, user.getUsername());
      }
    }
}
/*
  User user = userService.getUserInfoByUsername(username);
        List<Order> userOrders = orderService.getAllOrdersByUsername(username);
        double sumRate = 0;
        for(Iterator iterator = userOrders.iterator(); iterator.hasNext();){
            Order order = (Order) iterator.next();
            sumRate += order.getTotal();
        }
        if(sumRate >= 200){
            user.setDiscount(7.0);
            userService.patchUserInfo(user, user.getName());
        }
        if(sumRate >= 500){
            user.setDiscount(10.0);
            userService.patchUserInfo(user, user.getName());
        }
 */