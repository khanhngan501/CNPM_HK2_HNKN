package com.group08.onlineShop.repository;

import com.group08.onlineShop.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepo extends MongoRepository<Account, Long> {
    Optional<Account> findAccountByEmail(String email);
    Boolean existsByEmail(String email);
}
