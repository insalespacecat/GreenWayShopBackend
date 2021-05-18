package com.greenwayshop.learning.services;

import com.greenwayshop.learning.domain.Order;
import com.greenwayshop.learning.domain.AppUser;
import com.greenwayshop.learning.properties.DiscountProperties;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DiscountService {

    UserService userService;
    DiscountProperties discountProperties;

    void checkAndUpdateUserDiscountIfRequired(Order order) {
      AppUser user = order.getUser();
      user.setOrdersTotal(user.getOrdersTotal() + order.getTotal());
      if(user.getOrdersTotal() > discountProperties.getTotalforlevelthree()){
          user.setDiscount(discountProperties.getLevelthree());
          userService.patchUserInfo(user, user.getUsername());
          return;
      }
      if(user.getOrdersTotal() > discountProperties.getTotalforleveltwo()){
          user.setDiscount(discountProperties.getLeveltwo());
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