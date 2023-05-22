package com.group08.onlineShop.repository;

import com.group08.onlineShop.model.Account;
import com.group08.onlineShop.model.CartItem;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CartItemRepo extends MongoRepository<CartItem, Long> {
    List<CartItem> findCartItemsByAccount(Account account);
}
