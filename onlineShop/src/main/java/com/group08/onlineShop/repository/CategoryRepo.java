package com.group08.onlineShop.repository;

import com.group08.onlineShop.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CategoryRepo extends MongoRepository<Category, Long> {
    Category getReferenceById(Long categoryId);
}
