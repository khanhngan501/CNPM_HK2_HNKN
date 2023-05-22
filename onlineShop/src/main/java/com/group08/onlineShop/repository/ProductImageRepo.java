package com.group08.onlineShop.repository;

import com.group08.onlineShop.model.ProductImage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImageRepo extends MongoRepository<ProductImage, Long> {
    ProductImage getProductImageByProduct(Long id);
}
