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

    public AuthorDto addNewAuthor(AuthorDto authorDto) {
        Author author = mapFromDtoToAuthor(authorDto);
        return mapFromAuthorToDto(authorRepository.save(author));
    }

    public Author findAuthorById(long id) {
        return authorRepository.findById(id).orElseThrow();
    }

    public AuthorDto updateAuthorById(long id, AuthorDto authorDto) {
        Author author = mapFromDtoToAuthor(authorDto);
        Author oldAuthor = authorRepository.findById(id).orElseThrow();
        return authorRepository.findById(id).map((newAuthor) -> {
            newAuthor.setFirstname(Optional.ofNullable(author.getFirstname()).orElse(oldAuthor.getFirstname()));
            newAuthor.setLastname(Optional.ofNullable(author.getLastname()).orElse(oldAuthor.getLastname()));
            newAuthor.setBookList(Optional.ofNullable(author.getBookList()).orElse(oldAuthor.getBookList()));
            newAuthor.setRating(Optional.ofNullable(author.getRating()).orElse(oldAuthor.getRating()));
            return mapFromAuthorToDto(authorRepository.save(newAuthor));
        }).orElseGet(() -> {
            author.setAuthor_id(id);
            return mapFromAuthorToDto(authorRepository.save(author));
        });
    }

    public String deleteAuthorById(long id) {
        Optional<Author> author = authorRepository.findById(id);
        if (author.isEmpty()) {
            return "Autor " + id + " nie został usunięty. Powód: brak autora o podanym id";
        }
        else if (author.get().getBookList().isEmpty()) {
            authorRepository.deleteById(id);
            return "Autor " + id + " został usunięty.";
        }
        else return "Autor " + id + " nie został usunięty. Powód: powiązanie z inną tabelą bądź inne.";
    }

    public static Author mapFromDtoToAuthor(AuthorDto authorDto) {
        Author author = new Author();
        author.setAuthor_id(authorDto.getAuthor_id());
        author.setFirstname(authorDto.getFirstname());
        author.setLastname(authorDto.getLastname());
        author.setRating(authorDto.getRating());
        //author.setBookList(authorDto.getBookList());
        return author;
    }

    public static AuthorDto mapFromAuthorToDto(Author author) {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setAuthor_id(author.getAuthor_id());
        authorDto.setFirstname(author.getFirstname());
        authorDto.setLastname(author.getLastname());
        authorDto.setRating(author.getRating());
        //authorDto.setBookList(author.getBookList());
        return authorDto;
    }
}
