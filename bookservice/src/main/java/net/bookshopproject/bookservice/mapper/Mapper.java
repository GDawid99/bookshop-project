package net.bookshopproject.bookservice.mapper;

import net.bookshopproject.bookservice.dto.AuthorDto;
import net.bookshopproject.bookservice.dto.BookDto;
import net.bookshopproject.bookservice.model.Author;
import net.bookshopproject.bookservice.model.Book;
import net.bookshopproject.bookservice.service.AuthorService;

public class Mapper {
    public static Book mapFromDtoToBook(BookDto bookDto) {
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setAuthor(mapFromDtoToAuthor(bookDto.getAuthor()));
        book.setPublisher(bookDto.getPublisher());
        book.setDateOfPublication(bookDto.getDateOfPublication());
        book.setGenre(bookDto.getGenre());
        book.setPrice(bookDto.getPrice());
        book.setQuantity(bookDto.getQuantity());
        book.setRating(bookDto.getRating());
        book.setImageUrl(bookDto.getImageUrl());
        return book;
    }

    public static BookDto mapFromBookToDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setBook_id(book.getBook_id());
        bookDto.setTitle(book.getTitle());
        bookDto.setAuthor(mapFromAuthorToDto(book.getAuthor()));
        bookDto.setPublisher(book.getPublisher());
        bookDto.setDateOfPublication(book.getDateOfPublication());
        bookDto.setGenre(book.getGenre());
        bookDto.setPrice(book.getPrice());
        bookDto.setQuantity(book.getQuantity());
        bookDto.setRating(book.getRating());
        bookDto.setImageUrl(book.getImageUrl());
        return bookDto;
    }

    public static Author mapFromDtoToAuthor(AuthorDto authorDto) {
        Author author = new Author();
        author.setAuthor_id(authorDto.getAuthor_id());
        author.setFirstname(authorDto.getFirstname());
        author.setLastname(authorDto.getLastname());
        author.setRating(authorDto.getRating());
        return author;
    }

    public static AuthorDto mapFromAuthorToDto(Author author) {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setAuthor_id(author.getAuthor_id());
        authorDto.setFirstname(author.getFirstname());
        authorDto.setLastname(author.getLastname());
        authorDto.setRating(author.getRating());
        return authorDto;
    }
}
