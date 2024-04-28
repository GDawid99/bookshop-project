package net.bookshopproject.userservice.controller;

import net.bookshopproject.userservice.dto.UserDto;
import net.bookshopproject.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public UserDto addNewUser(@RequestBody UserDto userDto) {
        return userService.createNewUser(userDto);
    }

    @DeleteMapping("/remove/{id}")
    public String removeUser(@PathVariable long id) {
        userService.deleteUser(id);
        return "Użytkownik " + id + " został usunięty.";
    }

}
