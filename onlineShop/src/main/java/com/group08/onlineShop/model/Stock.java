package com.group08.onlineShop.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "stock")
public class Stock {
    @Transient
    public static final String SEQUENCE_NAME = "stock_sequence";
    @Id
    private Long id;
    private Product product;
    private String size;
    private String color;
    private Integer quantity;

    public Stock(Product product, String size, String color, Integer quantity) {
        this.product = product;
        this.size = size;
        this.color = color;
        this.quantity = quantity;
    }
}
