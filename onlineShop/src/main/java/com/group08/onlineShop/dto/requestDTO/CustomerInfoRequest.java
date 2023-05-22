package com.group08.onlineShop.dto.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerInfoRequest {
    private Long account;
    private String phoneNumber;
    private Long address;
    private String customerName;
    private Boolean isDefault;
}
