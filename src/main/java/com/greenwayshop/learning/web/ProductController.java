package com.greenwayshop.learning.web;

import com.greenwayshop.learning.domain.Product;
import com.greenwayshop.learning.api.ProductRepository;
import com.greenwayshop.learning.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Slf4j
@RestController
@RequestMapping("/query")
public class ProductController {
        private ProductRepository productRepository;
        private ProductService productService;
        ProductController(ProductRepository productRepository, ProductService productService){
            this.productRepository = productRepository;
            this.productService = productService;
        }

        @GetMapping(path = "/getProducts", produces="application/json")
        @ResponseStatus(HttpStatus.OK)
        public ArrayList<Product> getProducts() {
            ArrayList<Product> products = productRepository.findAll();
            log.info("get request");
            log.info(products.toString());
            return products;
        }

        @PostMapping(path = "/addProduct", consumes = "application/json")
        @ResponseStatus(HttpStatus.CREATED)
        public Product addProduct(@RequestBody Product product){
            product.setId(null);
            log.info("Post request");
            log.info(product.toString());
            return (Product) productRepository.save(product);
        }
        @PatchMapping(path="/patch/{productId}", consumes="application/json")
        public ResponseEntity<Product> patchProduct(@PathVariable("productId") Long productId, @RequestBody Product patch) {
            return productService.patch(productId, patch);
        }

        @DeleteMapping(path = "/delete/{productId}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void deleteProduct(@PathVariable("productId") Long productId) {
            log.info("Delete request!");
            log.info(productId.toString());
            try {
                productRepository.deleteById(productId);
            } catch (EmptyResultDataAccessException ignored) {}
        }
    }
