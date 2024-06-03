package net.bookshopproject.userservice.controller;

import net.bookshopproject.userservice.JwtResponse;
import net.bookshopproject.userservice.JwtValidateResponse;
import net.bookshopproject.userservice.Role;
import net.bookshopproject.userservice.dto.RequestUser;
import net.bookshopproject.userservice.dto.UserDto;
import net.bookshopproject.userservice.jwt.JwtUtils;
import net.bookshopproject.userservice.model.User;
import net.bookshopproject.userservice.service.UserDetailsImpl;
import net.bookshopproject.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    JwtUtils jwtUtils;

    //@PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('MODERATOR')")
    @GetMapping("/auth/validate")
    public ResponseEntity<?> validateJwt(@RequestHeader("Authorization") String token) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        boolean isCorrectToken = jwtUtils.validateJwtToken(token);
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        System.out.println(isCorrectToken + " " + roles);
        return ResponseEntity.ok(new JwtValidateResponse(isCorrectToken, roles));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/get")
    public ResponseEntity<List<String>> getUserById(@RequestBody RequestUser users) {
        return ResponseEntity.ok(userService.getUserById(users));
    }

    @PostMapping("/auth/register")
    public ResponseEntity<UserDto> addNewUser(@RequestBody UserDto userDto) {
        userService.createNewUser(userDto);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).build();
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> loginUser(@RequestBody UserDto userDto) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userDto.getEmail(),userDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        System.out.println(userDetails.getAuthorities());
        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUser_id(), userDetails.getEmail(), roles));
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/remove/{id}")
    public String removeUser(@PathVariable long id) {
        userService.deleteUser(id);
        return "Użytkownik " + id + " został usunięty.";
    }

}
