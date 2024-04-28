package net.bookshopproject.bookservice;

import net.bookshopproject.bookservice.model.Author;
import net.bookshopproject.bookservice.model.Book;
import net.bookshopproject.bookservice.repository.AuthorRepository;
import net.bookshopproject.bookservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Date;

@SpringBootApplication
public class BookserviceApplication implements CommandLineRunner {

	@Autowired
	AuthorRepository authorRepository;

	@Autowired
	BookRepository bookRepository;

	public static void main(String[] args) {
		SpringApplication.run(BookserviceApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		Author author1 = new Author(1L,"Adam","Mickiewicz",7);
		Author author2 = new Author(2L,"Stanisław","Lem",9);
		Author author3 = new Author(3L,"John R. R.","Tolkien",10);

		Book book1 = new Book(1L,"Pan Tadeusz",author1,"Teraz",new Date(622751400000L),"Literatura piękna","85.99",40,8,"pan_tadeusz.jpg");
		Book book2 = new Book(2L,"Dziady",author1,"Teatr",new Date(648435200000L),"Dramat","60.99",90,6,"dziady.jpg");

		Book book3 = new Book(3L,"Solaris",author2,"Nowy Świat",new Date(622729345010L),"Sci-Fi","63.19",56,10,"solaris.jpg");

		Book book4 = new Book(4L,"Władca Pierścieni: Drużyna Pierścienia",author3,"Teraz",new Date(622834619234L),"Fantastyka","78.39",12,9,"wladca_pierscieni.jpg");


		authorRepository.save(author1);
		authorRepository.save(author2);
		authorRepository.save(author3);

		bookRepository.save(book1);
		bookRepository.save(book2);
		bookRepository.save(book3);
		bookRepository.save(book4);
	}
}
