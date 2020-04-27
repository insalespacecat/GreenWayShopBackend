package com.greenwayshop.learning.web;

import com.greenwayshop.learning.domain.Product;
import com.greenwayshop.learning.api.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/query")
public class ProductController {
        private ProductRepository productRepository;
        ProductController(ProductRepository productRepository){
            this.productRepository = productRepository;
        }

        @GetMapping(path = "/getProducts", produces="application/json")
        @ResponseStatus(HttpStatus.OK)
        public ArrayList<Product> getEmployees() {
            ArrayList<Product> emps = productRepository.findAll();
            log.info("get request");
            log.info(emps.toString());
            return emps;
        }
        @PostMapping(path = "/addProduct", consumes = "application/json")
        @ResponseStatus(HttpStatus.CREATED)
        public Product addEmployee(@RequestBody Product product){
            product.setId(null);
            log.info("Post request");
            log.info(product.toString());
            return (Product) productRepository.save(product);
        }
        @PatchMapping(path="/patch/{productId}", consumes="application/json")
        public ResponseEntity<Product> patchEmployee(@PathVariable("productId") Long productId, @RequestBody Product patch) {
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
        @DeleteMapping(path = "/delete/{productId}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void deleteOrder(@PathVariable("productId") Long productId) {
            log.info("Delete request!");
            log.info(productId.toString());
            try {
                productRepository.deleteById(productId);
            } catch (EmptyResultDataAccessException ignored) {}
        }
    }
