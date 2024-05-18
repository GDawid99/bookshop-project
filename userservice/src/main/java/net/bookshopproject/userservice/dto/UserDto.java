package net.bookshopproject.userservice.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long user_id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String role;
}
