package com.greenwayshop.learning.services;

import com.greenwayshop.learning.api.ProductRepository;
import com.greenwayshop.learning.domain.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public ResponseEntity<Product> patch(Long productId, Product patch){
        Optional<Product> productOpt = productRepository.findById(productId);
        log.info("patch request");
        if(productOpt.isEmpty()) {
            return new ResponseEntity<Product>(patch, HttpStatus.NOT_FOUND);
        }
        Product productToPatch = productOpt.get();
        log.info(productToPatch.toString());
        if(patch.getName() != null){
            productToPatch.setName(patch.getName());
        }
        if(patch.getPrice() > 0){
            productToPatch.setPrice(patch.getPrice());

        }
        if(patch.getDescription() != null){
            productToPatch.setDescription(patch.getDescription());
        }
        return new ResponseEntity<Product>((Product)productRepository.save(productToPatch), HttpStatus.ACCEPTED);
    }
}
