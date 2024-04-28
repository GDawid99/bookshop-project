package net.bookshopproject.userservice.service;

import net.bookshopproject.userservice.dto.UserDto;
import net.bookshopproject.userservice.model.User;
import net.bookshopproject.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

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
        user.setStatus(userDto.getStatus());
        return user;
    }

    public UserDto mapFromUserToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUser_id(user.getUser_id());
        userDto.setFirstname(user.getFirstname());
        userDto.setLastname(user.getLastname());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setStatus(user.getStatus());
        return userDto;
    }
}
