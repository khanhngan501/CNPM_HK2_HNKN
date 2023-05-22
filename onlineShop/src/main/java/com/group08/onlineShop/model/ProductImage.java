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
@Document(collection = "product_image")
public class ProductImage {
    @Transient
    public static final String SEQUENCE_NAME = "product_image_sequence";
    @Id
    private Long id;
    private Product product;
    private String imageLink;
    private Integer isDefault;
    private String color;
}
