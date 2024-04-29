package net.bookshopproject.bookservice.service;

import net.bookshopproject.bookservice.dto.BookDto;
import net.bookshopproject.bookservice.model.Author;
import net.bookshopproject.bookservice.model.Book;
import net.bookshopproject.bookservice.repository.AuthorRepository;
import net.bookshopproject.bookservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;


    public List<Book> findBooksByPage(String title, long id, int size) {
        return bookRepository.findByTitleContaining(title, PageRequest.of((int)id,size))
                .stream()
                .toList();
    }

    public ResponseEntity<Object> findAllBooks() {
        List<Book> bookList = bookRepository.findAll();
        List<BookDto> bookDtoList = new ArrayList<>();
        for (Book b: bookList) {
            bookDtoList.add(mapFromBookToDto(b));
        }

        return ResponseEntity.ok(bookDtoList);
    }

    public ResponseEntity<Object> findThreeRandomBooks() {
        List<Book> books = bookRepository.findAll();
        Collections.shuffle(books);
        List<BookDto> randomBooklist = Arrays.asList(mapFromBookToDto(books.get(0)), mapFromBookToDto(books.get(1)), mapFromBookToDto(books.get(2)));
        return ResponseEntity.ok(randomBooklist);
    }

    public Book findBookByTitle(String title, long author_id) {
        Author author = authorRepository.getReferenceById(author_id);
        Book book = bookRepository.findByTitle(title).orElseThrow();
        if (author.getAuthor_id() != book.getAuthor().getAuthor_id()) {
            throw new IllegalStateException("Rozne id");
        }
        return book;
    }

    public Book createNewBook(BookDto bookDto, long author_id) {
        Author author = authorRepository.findById(author_id).orElseThrow();

        Book book = mapFromDtoToBook(bookDto);
        book.setAuthor(author);

        return bookRepository.save(book);
    }

    public void deleteBookById(long id) {
        bookRepository.deleteById(id);
    }

    public Book updateBookById(long id, BookDto bookDto) {
        Book book = mapFromDtoToBook(bookDto);
        Book oldBook = bookRepository.getReferenceById(id);
        return bookRepository.findById(id).map((newBook) -> {
            newBook.setTitle(Optional.ofNullable(book.getTitle()).orElse(oldBook.getTitle()));
            newBook.setAuthor(Optional.ofNullable(book.getAuthor()).orElse(oldBook.getAuthor()));
            newBook.setPublisher(Optional.ofNullable(book.getPublisher()).orElse(oldBook.getPublisher()));
            newBook.setDateOfPublication(Optional.ofNullable(book.getDateOfPublication()).orElse(oldBook.getDateOfPublication()));
            newBook.setGenre(Optional.ofNullable(book.getGenre()).orElse(oldBook.getGenre()));
            newBook.setPrice(Optional.ofNullable(book.getPrice()).orElse(oldBook.getPrice()));
            newBook.setQuantity(Optional.ofNullable(book.getQuantity()).orElse(oldBook.getQuantity()));
            newBook.setRating(Optional.ofNullable(book.getRating()).orElse(oldBook.getRating()));
            newBook.setImageUrl(Optional.ofNullable(book.getImageUrl()).orElse(oldBook.getImageUrl()));
            return bookRepository.save(newBook);
        }).orElseGet(() -> {
            book.setBook_id(id);
            return bookRepository.save(book);
        });
    }

    private Book mapFromDtoToBook(BookDto bookDto) {
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setPublisher(bookDto.getPublisher());
        book.setDateOfPublication(bookDto.getDateOfPublication());
        book.setGenre(bookDto.getGenre());
        book.setPrice(bookDto.getPrice());
        book.setQuantity(bookDto.getQuantity());
        book.setRating(bookDto.getRating());
        book.setImageUrl(bookDto.getImageUrl());
        return book;
    }

    private BookDto mapFromBookToDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setBook_id(book.getBook_id());
        bookDto.setTitle(book.getTitle());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setPublisher(book.getPublisher());
        bookDto.setDateOfPublication(book.getDateOfPublication());
        bookDto.setGenre(book.getGenre());
        bookDto.setPrice(book.getPrice());
        bookDto.setQuantity(book.getQuantity());
        bookDto.setRating(book.getRating());
        bookDto.setImageUrl(book.getImageUrl());
        return bookDto;
    }
}
