package com.group08.onlineShop.dto.responseDTO;

import lombok.Data;

@Data
public class AccountResponseDTO {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private Long role_id;
    private String password;
    private Boolean active;

    public AccountResponseDTO(Long id, String email, String firstName, String lastName, Long role_id, Boolean active) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role_id = role_id;
        this.active = active;
    }

}
