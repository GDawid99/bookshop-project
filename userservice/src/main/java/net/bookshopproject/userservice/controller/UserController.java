package net.bookshopproject.userservice.controller;

import net.bookshopproject.userservice.dto.UserDto;
import net.bookshopproject.userservice.model.User;
import net.bookshopproject.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public UserDto addNewUser(@RequestBody UserDto userDto) {
        //User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.createNewUser(userDto);
    }

    @DeleteMapping("/remove/{id}")
    //@PreAuthorize("hasAnyRole('ADMIN')")
    public String removeUser(@PathVariable long id) {
        userService.deleteUser(id);
        return "Użytkownik " + id + " został usunięty.";
    }

}
