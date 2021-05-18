package com.greenwayshop.learning.services;

import com.greenwayshop.learning.api.ProductRepository;
import com.greenwayshop.learning.domain.Product;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import static com.greenwayshop.learning.services.CheckMethods.checkForEmptyAndThrowResponseTypeExcIfRequired;
import static com.greenwayshop.learning.services.CheckMethods.checkForNullAndTrowResponseTypeExcIfRequired;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class ProductService {

    ProductRepository productRepository;

    public Product patch(Long productId, Product patch){
        log.info(patch.toString());
        if(patch.getName() != null){
            patch.setName(patch.getName());
        }
        if(patch.getPrice() > 0){
            patch.setPrice(patch.getPrice());

        }
        if(patch.getDescription() != null){
            patch.setDescription(patch.getDescription());
        }
        return productRepository.save(patch);
    }

    public Product saveProduct(Product product){
        checkForNullAndTrowResponseTypeExcIfRequired(product);
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
