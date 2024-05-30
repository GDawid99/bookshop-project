package net.bookshopproject.orderservice;

import net.bookshopproject.orderservice.model.Cart;
import net.bookshopproject.orderservice.model.CartElement;
import net.bookshopproject.orderservice.repository.CartElementRepository;
import net.bookshopproject.orderservice.repository.CartRepository;
import net.bookshopproject.orderservice.service.CartElementService;
import net.bookshopproject.orderservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

@SpringBootApplication
public class OrderserviceApplication implements CommandLineRunner{

	@Autowired
	CartRepository cartRepository;

	@Autowired
	CartElementRepository cartElementRepository;

	public static void main(String[] args) {
		SpringApplication.run(OrderserviceApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		Cart cart1 = new Cart(1,1,new ArrayList<>(), LocalDate.of(2024,3,20), Status.DELIVERED.name());
		Cart cart2 = new Cart(2,6,new ArrayList<>(), LocalDate.of(2024,1,24), Status.CANCELLED.name());
		Cart cart3 = new Cart(3,3,new ArrayList<>(), LocalDate.of(2024,1,11), Status.NEW.name());


		CartElement element1 = new CartElement(1,cart1,1,3);
		CartElement element2 = new CartElement(2,cart1,2,5);
		CartElement element3 = new CartElement(3,cart2,3,1);
		CartElement element4 = new CartElement(4,cart1,4,1);
		CartElement element5 = new CartElement(5,cart3,2,2);


		cartRepository.save(cart1);
		cartRepository.save(cart2);
		cartRepository.save(cart3);
		cartElementRepository.save(element1);
		cartElementRepository.save(element2);
		cartElementRepository.save(element3);
		cartElementRepository.save(element4);
		cartElementRepository.save(element5);

	}
}
