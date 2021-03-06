package com.greenwayshop.learning.web;

import com.greenwayshop.learning.domain.Product;
import com.greenwayshop.learning.services.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/query")
@AllArgsConstructor
public class ProductController {

        private ProductService productService;

        @GetMapping(path = "/getProducts", produces="application/json")
        public List<Product> getProducts() {
            return productService.getAllProducts();
        }

        @PostMapping(path = "/addProduct", consumes = "application/json")
        public Product addProduct(@RequestBody Product product){
            return productService.saveProduct(product);
        }

        @PatchMapping(path="/patch/{productId}", consumes="application/json")
        public Product patchProduct(@PathVariable("productId") Long productId, @RequestBody Product patch) {
            return productService.patch(productId, patch);
        }

        @DeleteMapping(path = "/delete/{productId}")
        public void deleteProduct(@PathVariable("productId") Long productId) {
            productService.deleteProductById(productId);
        }
}
