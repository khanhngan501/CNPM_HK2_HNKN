package com.group08.onlineShop.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "product")
public class Product {
    @Transient
    public static final String SEQUENCE_NAME = "product_sequence";
    @Id
    private Long id;
    private String productName;
    private Double price;
    private Category category;
    private TypeProduct type;
    private ProductImage productImage;
    private String description;
}
