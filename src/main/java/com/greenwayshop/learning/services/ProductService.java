package com.greenwayshop.learning.services;

import com.greenwayshop.learning.api.ProductRepository;
import com.greenwayshop.learning.domain.Product;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import static com.greenwayshop.learning.services.CheckMethods.checkForEmptyAndThrowResponseTypeExcIfNeeded;
import static com.greenwayshop.learning.services.CheckMethods.checkForNullAndTrowResponseTypeExcIfNeeded;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class ProductService {

    ProductRepository productRepository;

    public Product patch(Long productId, Product patch){
        Optional optProduct = Optional.ofNullable(productRepository.findById(productId));
        checkForEmptyAndThrowResponseTypeExcIfNeeded(optProduct);
        Product productToPatch = (Product) optProduct.get();
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
        return productRepository.save(productToPatch);
    }

    public Product saveProduct(Product product){
        checkForNullAndTrowResponseTypeExcIfNeeded(product);
        product.setId(null);
        return productRepository.save(product);
    }

    public void deleteProductById(Long productId){
        productRepository.deleteById(productId);
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
}
