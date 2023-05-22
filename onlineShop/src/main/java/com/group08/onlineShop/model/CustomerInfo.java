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
@Document(collection = "customer_info")
public class CustomerInfo {
    @Transient
    public static final String SEQUENCE_NAME = "customer_info_sequence";
    @Id
    private Long id;
    private Account account;
    private String phoneNumber;
    private Address address;
    private String customerName;
    private Boolean isDefault;
}
