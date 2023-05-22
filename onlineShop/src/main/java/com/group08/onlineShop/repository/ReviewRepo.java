package com.group08.onlineShop.repository;

import com.group08.onlineShop.model.Account;
import com.group08.onlineShop.model.Product;
import com.group08.onlineShop.model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepo extends MongoRepository<Review, Long> {
    Optional<List<Review>> findReviewByAccount(Account account);

    Optional<List<Review>> findReviewByProduct(Product product);
}
