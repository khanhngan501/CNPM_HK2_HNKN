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
@Document(collection = "review")
public class Review {
    @Transient
    public static final String SEQUENCE_NAME = "review_sequence";
    @Id
    private Long id;
    private Account account;
    private Product product;
    private Instant reviewCreateAt;
    private String reviewContent;
    private Double reviewRate;
    private Integer reviewLike;
    private Integer reviewDislike;

    public Review(Account account, Product product, Instant reviewCreateAt, String reviewContent,
                  Double reviewRate, Integer reviewLike, Integer reviewDislike) {
        this.account = account;
        this.product = product;
        this.reviewCreateAt = reviewCreateAt;
        this.reviewContent = reviewContent;
        this.reviewRate = reviewRate;
        this.reviewLike = reviewLike;
        this.reviewDislike = reviewDislike;
    }
}
