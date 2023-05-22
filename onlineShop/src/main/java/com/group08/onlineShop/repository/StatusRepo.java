package com.group08.onlineShop.repository;

import com.group08.onlineShop.model.Status;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepo extends MongoRepository<Status, Long> {
}
