package com.group08.onlineShop.service.impl;

import com.group08.onlineShop.dto.requestDTO.ProductReq;
import com.group08.onlineShop.dto.responseDTO.SearchProductResp;
import com.group08.onlineShop.exception.AppException;
import com.group08.onlineShop.exception.ResourceNotFoundException;
import com.group08.onlineShop.model.Category;
import com.group08.onlineShop.model.Product;
import com.group08.onlineShop.model.ProductImage;
import com.group08.onlineShop.model.TypeProduct;
import com.group08.onlineShop.repository.CategoryRepo;
import com.group08.onlineShop.repository.ProductImageRepo;
import com.group08.onlineShop.repository.ProductRepo;
import com.group08.onlineShop.service.ProductService;
import com.group08.onlineShop.service.SequenceGeneratorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceIpml implements ProductService {
    private final CategoryRepo categoryRepo;
    private final ProductRepo productRepo;
    private final SequenceGeneratorService sequenceGeneratorService;

    @Override
    public Product saveNewProduct(Product productReq) {
        Category category = categoryRepo.findById(productReq.getCategory().getId()).orElse(null);
        if(category!=null){
            Long product_id = Long.valueOf(sequenceGeneratorService.generateSequence(productReq.SEQUENCE_NAME));
            Product product = new Product();
            product.setId(product_id);
            product.setCategory(category);
            product.setProductName(productReq.getProductName());
            product.setType(productReq.getType());
            product.setPrice(productReq.getPrice());
            product.setDescription(productReq.getDescription());
            productRepo.save(product);
            return product;
        }
        return null;

    }

    @Override
    public List<Product> findAll() {
        return productRepo.findAll();
    }

    @Override
    public Product findById(Long productId) throws ResourceNotFoundException {
        var product = productRepo.findById(productId).
                orElseThrow(() -> new ResourceNotFoundException("Product", "productID", Long.toString(productId)));
        return product;
    }

    @Override
    public Product updateProduct(ProductReq productReq) throws ResourceNotFoundException {
        Product productUpdate = findById(productReq.getId());
        Category category = categoryRepo.findById(productReq.getCategory()).orElseThrow(()
        -> new ResourceNotFoundException("Category", "categoryID", productReq.getCategory()));
        productUpdate.setCategory(category);
        productUpdate.setPrice(productReq.getPrice());
        productUpdate.setProductName(productReq.getProductName());
        return productUpdate;
    }
    @Override
    public List<Product> suggestProduct(ProductReq productReq) throws ResourceNotFoundException {
        Product product = findById(productReq.getId());
        List<Product> productAll = findAll();
        List<Product> sugProd = new ArrayList<>();
        productAll.forEach(
                pA ->{
                    if(pA.getCategory() == product.getCategory())
                    {
                        sugProd.add(pA);
                    }
                    else if(pA.getType() == product.getType())
                    {
                        sugProd.add(pA);
                    }
                }
        );
        return sugProd;
    }

    @Override
    public boolean deleteProductById(Long id) {
        Product productDelete = productRepo.findById(id).orElse(null);
        if (productDelete != null) {
            productRepo.deleteById(id);
            return true;
        } else {
            throw new AppException(404, "Product ID not found");
        }
    }
}
