package net.bookshopproject.userservice;

import net.bookshopproject.userservice.model.User;
import net.bookshopproject.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class UserserviceApplication implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(UserserviceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user1 = new User(1L,"Emil","Nowak","enowak@example.com", passwordEncoder.encode("qwerty"),Role.USER.name());
		User user2 = new User(2L,"Adam","Wójcik","adamwojcik@store.com", passwordEncoder.encode("qwerty"),Role.ADMIN.name());
		User user3 = new User(3L,"Agata","Sikora","agasikora12@example.com", passwordEncoder.encode("12345"),Role.USER.name());
		User user4 = new User(4L,"Marcin","Leszczyński","marcinleszcz@example.com", passwordEncoder.encode("qwerty123"),Role.USER.name());
		User user5 = new User(5L,"Damian","Kowalski","damianok01@cos.pl", passwordEncoder.encode("wasd"),Role.USER.name());
		User user6 = new User(6L,"Zofia","Koło","kolozofia20@example.com", passwordEncoder.encode("qwerty"),Role.USER.name());

		userRepository.save(user1);
		userRepository.save(user2);
		userRepository.save(user3);
		userRepository.save(user4);
		userRepository.save(user5);
		userRepository.save(user6);

	}
}
