package com.greenwayshop.learning.api;

import com.greenwayshop.learning.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    void deleteById(Long id);
    ArrayList<Product> findAll();
}
