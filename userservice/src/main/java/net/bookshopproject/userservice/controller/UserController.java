package net.bookshopproject.userservice.controller;

import net.bookshopproject.userservice.dto.UserDto;
import net.bookshopproject.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> addNewUser(@RequestBody UserDto userDto) {
        userService.createNewUser(userDto);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).build();
    }

    @DeleteMapping("/remove/{id}")
    public String removeUser(@PathVariable long id) {
        userService.deleteUser(id);
        return "Użytkownik " + id + " został usunięty.";
    }

}
