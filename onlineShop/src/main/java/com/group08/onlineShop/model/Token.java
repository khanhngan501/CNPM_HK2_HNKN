package com.group08.onlineShop.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Token {
    @Transient
    public static final String SEQUENCE_NAME = "token_sequence";
    @Id
    private Long id;
    private Account account;
    private String code;
    private Date expiryDate;
}
