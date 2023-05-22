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
@Document(collection = "status")
public class Status {
    @Transient
    public static final String SEQUENCE_NAME = "status_sequence";
    @Id
    private Long id;
    private String status;
}
