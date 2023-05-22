package com.group08.onlineShop.repository;

import com.group08.onlineShop.model.Token;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepo extends MongoRepository<Token, Long> {

    Token findTokenByCode(String token);
}
