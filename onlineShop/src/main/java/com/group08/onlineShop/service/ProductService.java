package com.group08.onlineShop.service;

import com.group08.onlineShop.dto.requestDTO.ProductReq;
import com.group08.onlineShop.dto.responseDTO.SearchProductResp;
import com.group08.onlineShop.exception.ResourceNotFoundException;
import com.group08.onlineShop.model.Product;
import com.group08.onlineShop.model.TypeProduct;

import java.util.List;

public interface ProductService {
    Product saveNewProduct(Product productReq);

    Product findById(Long productId) throws ResourceNotFoundException;
    List<Product> suggestProduct(ProductReq productReq) throws ResourceNotFoundException;

    List<Product> findAll();

    Product updateProduct(ProductReq productReq) throws ResourceNotFoundException;

    boolean deleteProductById(Long id);

}
