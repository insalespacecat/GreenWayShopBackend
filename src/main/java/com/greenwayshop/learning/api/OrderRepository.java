package com.greenwayshop.learning.api;

import com.greenwayshop.learning.domain.Order;
import com.greenwayshop.learning.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAll();
    List<Order> findAllByUserId(Long userId);
    Order findTopByOrderByIdDesc();
}
