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
@Document(collection = "cart_item")
public class CartItem {
    @Transient
    public static final String SEQUENCE_NAME = "cart_item_sequence";
    @Id
    private Long id;
    private Product product;
    private Integer quantity;
    private Double totalPrice;
    private String size;
    private String color;
    private Account account;

    public CartItem(Product product, Integer quantity, Double totalPrice, String size, String color, Account account) {
        this.product = product;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.size = size;
        this.color = color;
        this.account = account;
    }
}
