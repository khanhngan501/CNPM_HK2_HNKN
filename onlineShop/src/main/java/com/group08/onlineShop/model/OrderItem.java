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
@Document(collection = "order_item")
public class OrderItem {
    @Transient
    public static final String SEQUENCE_NAME = "order_item_sequence";
    @Id
    private Long id;
    private Product product;
    private Integer quantity;
    private Double totalPrice;
    private String size;
    private String color;
    private Order order;
}
