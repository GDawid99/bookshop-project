package net.bookshopproject.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bookshopproject.userservice.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long user_id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Role role;
}
