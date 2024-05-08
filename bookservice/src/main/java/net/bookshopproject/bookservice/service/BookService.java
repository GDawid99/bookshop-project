package net.bookshopproject.bookservice.service;

import net.bookshopproject.bookservice.dto.BookDto;
import net.bookshopproject.bookservice.mapper.Mapper;
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


    public List<BookDto> findBooksByPage(String title, long id, int size) {
        List<Book> bookList = bookRepository.findByTitleContaining(title, PageRequest.of((int)id,size))
                .stream()
                .toList();
        List<BookDto> bookDtoList = new ArrayList<>();
        for (Book b: bookList) {
            bookDtoList.add(Mapper.mapFromBookToDto(b));
        }
        return bookDtoList;
    }

    public ResponseEntity<Object> findAllBooks() {
        List<Book> bookList = bookRepository.findAll();
        List<BookDto> bookDtoList = new ArrayList<>();
        for (Book b: bookList) {
            bookDtoList.add(Mapper.mapFromBookToDto(b));
        }

        return ResponseEntity.ok(bookDtoList);
    }

    public ResponseEntity<Object> findThreeRandomBooks() {
        List<Book> books = bookRepository.findAll();
        Collections.shuffle(books);
        List<BookDto> randomBooklist = Arrays.asList(Mapper.mapFromBookToDto(books.get(0)), Mapper.mapFromBookToDto(books.get(1)), Mapper.mapFromBookToDto(books.get(2)));
        return ResponseEntity.ok(randomBooklist);
    }

    public ResponseEntity<Object> findBookByTitle(String title, long author_id) {
        Author author = authorRepository.getReferenceById(author_id);
        Book book = bookRepository.findByTitle(title).orElseThrow();
        if (!Objects.equals(author.getAuthor_id(), book.getAuthor().getAuthor_id())) {
            throw new IllegalStateException("Rozne id");
        }
        BookDto bookDto = Mapper.mapFromBookToDto(book);
        return ResponseEntity.ok(book);
    }

    public Book createNewBook(BookDto bookDto, long author_id) {
        Author author = authorRepository.findById(author_id).orElseThrow();

        Book book = Mapper.mapFromDtoToBook(bookDto);
        book.setAuthor(author);

        return bookRepository.save(book);
    }

    public void deleteBookById(long id) {
        bookRepository.deleteById(id);
    }

    public Book updateBookById(long id, BookDto bookDto) {
        Book book = Mapper.mapFromDtoToBook(bookDto);
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
}
