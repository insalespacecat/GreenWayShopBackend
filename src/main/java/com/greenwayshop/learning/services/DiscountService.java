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