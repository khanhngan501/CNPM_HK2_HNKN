package com.group08.onlineShop.repository;

import com.group08.onlineShop.model.Product;
import com.group08.onlineShop.model.Stock;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface StockRepo extends MongoRepository<Stock, Long> {
    List<Stock> findStocksByProduct(Product product);
    Optional<Stock> findStockByProductAndColorAndSize(Product product, String color, String size);
}
