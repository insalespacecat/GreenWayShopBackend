package com.greenwayshop.learning.api;

import com.greenwayshop.learning.domain.Order;
import com.greenwayshop.learning.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    //"findAll() in orderRepository clashes with findAll() in JpaRepository
    //attempting to use incompatible return type" if Optional<List<Order>> is used
    List<Order> findAll();
    Optional<List<Order>> findAllByUser(User user);
    Order findTopByOrderByIdDesc();
}
