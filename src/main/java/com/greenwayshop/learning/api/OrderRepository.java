package com.greenwayshop.learning.api;

import com.greenwayshop.learning.domain.Order;
import com.greenwayshop.learning.domain.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    //"findAll() in orderRepository clashes with findAll() in JpaRepository
    //attempting to use incompatible return type" if Optional<List<Order>> is used
    List<Order> findAll();
    Page<Order> findAllByOrderByIdDesc(Pageable pageable);
    Optional<List<Order>> findAllByUser(AppUser user);
    Order findTopByOrderByIdDesc();
}
