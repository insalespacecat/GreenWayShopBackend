package com.greenwayshop.learning.api;

import com.greenwayshop.learning.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    ArrayList<Order> findAll();
    Order findTopByOrderByIdDesc();
}
