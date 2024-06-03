package net.bookshopproject.userservice;

import net.bookshopproject.userservice.dto.UserDto;
import net.bookshopproject.userservice.model.User;
import net.bookshopproject.userservice.repository.UserRepository;
import net.bookshopproject.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class UserserviceApplication implements CommandLineRunner {

	@Autowired
	UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(UserserviceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		UserDto user1 = new UserDto(1L,"Emil","Nowak","enowak@example.com", "qwerty",Role.ROLE_USER);
		UserDto user2 = new UserDto(2L,"Adam","Wójcik","adamwojcik@store.com", "qwerty",Role.ROLE_ADMIN);
		UserDto user3 = new UserDto(3L,"Agata","Sikora","agasikora12@example.com", "12345",Role.ROLE_USER);
		UserDto user4 = new UserDto(4L,"Marcin","Leszczyński","marcinleszcz@example.com", "qwerty123",Role.ROLE_USER);
		UserDto user5 = new UserDto(5L,"Damian","Kowalski","damianok01@cos.pl", "wasd",Role.ROLE_USER);
		UserDto user6 = new UserDto(6L,"Zofia","Koło","kolozofia20@example.com", "qwerty",Role.ROLE_USER);

		userService.createNewUser(user1);
		userService.createNewUser(user2);
		userService.createNewUser(user3);
		userService.createNewUser(user4);
		userService.createNewUser(user5);
		userService.createNewUser(user6);


	}
}
