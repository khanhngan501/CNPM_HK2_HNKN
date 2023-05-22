package com.group08.onlineShop.dto.requestDTO;

import lombok.Data;

@Data
public class AccountRequestDTO {
    private String email;
    private String firstName;
    private String lastName;
    private Long role_id;
    private String password;
    private Boolean active;
}
