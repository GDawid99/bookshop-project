package net.bookshopproject.userservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="bookshopuser")
public class User {
    @Id
    @GeneratedValue
    private Long user_id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String role;
}
