package net.bookshopproject.bookservice.service;

import net.bookshopproject.bookservice.dto.AuthorDto;
import net.bookshopproject.bookservice.model.Author;
import net.bookshopproject.bookservice.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    public Author addNewAuthor(AuthorDto authorDto) {
        Author author = mapFromDtoToAuthor(authorDto);
        return authorRepository.save(author);
    }

    public Author findAuthorById(long id) {
        return authorRepository.findById(id).orElseThrow();
    }

    public Author updateAuthorById(long id, AuthorDto authorDto) {
        Author author = mapFromDtoToAuthor(authorDto);
        Author oldAuthor = authorRepository.getReferenceById(id);
        return authorRepository.findById(id).map((newAuthor) -> {
            newAuthor.setFirstname(Optional.ofNullable(author.getFirstname()).orElse(oldAuthor.getFirstname()));
            newAuthor.setLastname(Optional.ofNullable(author.getLastname()).orElse(oldAuthor.getLastname()));
            newAuthor.setBookList(Optional.ofNullable(author.getBookList()).orElse(oldAuthor.getBookList()));
            newAuthor.setRating(Optional.ofNullable(author.getRating()).orElse(oldAuthor.getRating()));
            return authorRepository.save(newAuthor);
        }).orElseGet(() -> {
            author.setAuthor_id(id);
            return authorRepository.save(author);
        });
    }

    public String deleteAuthorById(long id) {
        authorRepository.deleteById(id);
        return "Autor " + id + " został usunięty.";
    }

    private Author mapFromDtoToAuthor(AuthorDto authorDto) {
        Author author = new Author();
        author.setFirstname(authorDto.getFirstname());
        author.setLastname(authorDto.getLastname());
        author.setRating(authorDto.getRating());
        author.setBookList(authorDto.getBookList());
        return author;
    }
}
