package net.bookshopproject.userservice.service;

import net.bookshopproject.userservice.dto.UserDto;
import net.bookshopproject.userservice.model.User;
import net.bookshopproject.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(this::mapFromUserToDto).toList();
    }

    public UserDto createNewUser(UserDto userDto) {
        User user = mapFromDtoToUser(userDto);
        return mapFromUserToDto(userRepository.save(user));
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }


    public User mapFromDtoToUser(UserDto userDto) {
        User user = new User();
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        return user;
    }

    public UserDto mapFromUserToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUser_id(user.getUser_id());
        userDto.setFirstname(user.getFirstname());
        userDto.setLastname(user.getLastname());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setRole(user.getRole());
        return userDto;
    }
}
