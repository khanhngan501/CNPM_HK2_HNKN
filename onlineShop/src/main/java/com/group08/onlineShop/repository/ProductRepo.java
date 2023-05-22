package com.group08.onlineShop.repository;

import com.group08.onlineShop.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ProductRepo extends MongoRepository<Product, Long> {
}
