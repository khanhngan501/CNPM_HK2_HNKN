package com.group08.onlineShop.dto.requestDTO;

import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
}
