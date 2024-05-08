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

    public List<BookDto> findAllBooks() {
        List<Book> bookList = bookRepository.findAll();
        List<BookDto> bookDtoList = new ArrayList<>();
        for (Book b: bookList) {
            bookDtoList.add(Mapper.mapFromBookToDto(b));
        }
        return bookDtoList;
    }

    public List<BookDto> findThreeRandomBooks() {
        List<Book> books = bookRepository.findAll();
        Collections.shuffle(books);
        return Arrays.asList(
                Mapper.mapFromBookToDto(books.get(0)),
                Mapper.mapFromBookToDto(books.get(1)),
                Mapper.mapFromBookToDto(books.get(2))
        );
    }

    public BookDto findBookByTitle(String title, long author_id) {
        Author author = authorRepository.getReferenceById(author_id);
        Book book = bookRepository.findByTitle(title).orElseThrow();
        if (!Objects.equals(author.getAuthor_id(), book.getAuthor().getAuthor_id())) {
            throw new IllegalStateException("Rozne id");
        }
        return Mapper.mapFromBookToDto(book);
    }

    public BookDto createNewBook(BookDto bookDto, long author_id) {
        Author author = authorRepository.findById(author_id).orElseThrow();
        bookDto.setAuthor(Mapper.mapFromAuthorToDto(author));
        Book book = Mapper.mapFromDtoToBook(bookDto);
        return Mapper.mapFromBookToDto(bookRepository.save(book));
    }

    public String deleteBookById(long id) {
        bookRepository.deleteById(id);
        return "Książka " + id + " została usunięta.";
    }

    public BookDto updateBookById(long id, BookDto bookDto) {
        Author author = bookRepository.getReferenceById(id).getAuthor();
        bookDto.setAuthor(Mapper.mapFromAuthorToDto(author));
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
            return Mapper.mapFromBookToDto(bookRepository.save(newBook));
        }).orElseGet(() -> {
            book.setBook_id(id);
            return Mapper.mapFromBookToDto(bookRepository.save(book));
        });
    }
}
