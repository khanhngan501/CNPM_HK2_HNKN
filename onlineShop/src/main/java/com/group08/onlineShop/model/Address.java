package com.group08.onlineShop.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document(collection = "address")
public class Address {
    @Transient
    public static final String SEQUENCE_NAME = "address_sequence";
    @Id
    private Long id;
    private String city;
    private String district;
    private String commune;
    private String detailAddress;
}
