package net.bookshopproject.bookservice;

import net.bookshopproject.bookservice.mapper.Mapper;
import net.bookshopproject.bookservice.model.Author;
import net.bookshopproject.bookservice.model.Book;
import net.bookshopproject.bookservice.model.Rating;
import net.bookshopproject.bookservice.repository.AuthorRepository;
import net.bookshopproject.bookservice.repository.BookRepository;
import net.bookshopproject.bookservice.repository.RatingRepository;
import net.bookshopproject.bookservice.service.RatingService;
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

	@Autowired
	RatingRepository ratingRepository;

	public static void main(String[] args) {
		SpringApplication.run(BookserviceApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		Author author1 = new Author(1L,"Adam","Mickiewicz",7);
		Author author2 = new Author(2L,"Stanisław","Lem",9);
		Author author3 = new Author(3L,"John R. R.","Tolkien",10);

		Book book1 = new Book(1L,"Pan Tadeusz",author1,"Teraz",new Date(622751400000L),"Literatura piękna","85.99",40,5.25f,"pan_tadeusz.jpg");
		Book book2 = new Book(2L,"Dziady",author1,"Teatr",new Date(648435200000L),"Dramat","60.99",90,6f,"dziady.jpg");

		Book book3 = new Book(3L,"Solaris",author2,"Nowy Świat",new Date(622729345010L),"Sci-Fi","63.19",56,5.5f,"solaris.jpg");

		Book book4 = new Book(4L,"Władca Pierścieni: Drużyna Pierścienia",author3,"Teraz",new Date(622834619234L),"Fantastyka","78.39",12,7.66f,"wladca_pierscieni.jpg");


		//Rating rating1 = new Rating(1L,book4,1L,"Super",10);
		Rating rating2 = new Rating(1L,book2,4L,"Ok",6);
		Rating rating3 = new Rating(2L,book3,3L,"Słabe",2);
		Rating rating4 = new Rating(3L,book1,1L,"",4);
		Rating rating5 = new Rating(4L,book3,2L,"",9);
		Rating rating6 = new Rating(5L,book1,6L,"ok",6);
		Rating rating7 = new Rating(6L,book4,5L,"dobre",8);
		Rating rating8 = new Rating(7L,book4,2L,"średnie",5);
		Rating rating9 = new Rating(8L,book1,3L,"",9);
		Rating rating10 = new Rating(9L,book1,1L,"",2);

		authorRepository.save(author1);
		authorRepository.save(author2);
		authorRepository.save(author3);

		bookRepository.save(book1);
		bookRepository.save(book2);
		bookRepository.save(book3);
		bookRepository.save(book4);

		//ratingRepository.save(rating1);
		ratingRepository.save(rating2);
		ratingRepository.save(rating3);
		ratingRepository.save(rating4);
		ratingRepository.save(rating5);
		ratingRepository.save(rating6);
		ratingRepository.save(rating7);
		ratingRepository.save(rating8);
		ratingRepository.save(rating9);
		ratingRepository.save(rating10);


	}
}
