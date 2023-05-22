package com.group08.onlineShop.repository;

import com.group08.onlineShop.model.Address;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepo extends MongoRepository<Address, Long> {
}
