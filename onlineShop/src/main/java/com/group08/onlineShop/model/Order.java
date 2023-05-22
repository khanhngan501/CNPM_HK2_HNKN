package com.group08.onlineShop.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "order")
public class Order {
    @Transient
    public static final String SEQUENCE_NAME = "order_sequence";
    @Id
    private Long id;
    private Account account;
    private Instant createAt;
    private Instant updateAt;
    private String receiverName;
    private String receiverPhoneNumber;
    private Address address;
    private Double deliveryChargers;
    private Double totalPrice;
    private Status status;
}
