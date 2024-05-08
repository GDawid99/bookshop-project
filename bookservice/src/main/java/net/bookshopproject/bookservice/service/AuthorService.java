package net.bookshopproject.bookservice.service;

import net.bookshopproject.bookservice.dto.AuthorDto;
import net.bookshopproject.bookservice.mapper.Mapper;
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
        Author author = Mapper.mapFromDtoToAuthor(authorDto);
        return Mapper.mapFromAuthorToDto(authorRepository.save(author));
    }

    public Author findAuthorById(long id) {
        return authorRepository.findById(id).orElseThrow();
    }

    public AuthorDto updateAuthorById(long id, AuthorDto authorDto) {
        Author author = Mapper.mapFromDtoToAuthor(authorDto);
        Author oldAuthor = authorRepository.findById(id).orElseThrow();
        return authorRepository.findById(id).map((newAuthor) -> {
            newAuthor.setFirstname(Optional.ofNullable(author.getFirstname()).orElse(oldAuthor.getFirstname()));
            newAuthor.setLastname(Optional.ofNullable(author.getLastname()).orElse(oldAuthor.getLastname()));
            newAuthor.setBookList(Optional.ofNullable(author.getBookList()).orElse(oldAuthor.getBookList()));
            newAuthor.setRating(Optional.ofNullable(author.getRating()).orElse(oldAuthor.getRating()));
            return Mapper.mapFromAuthorToDto(authorRepository.save(newAuthor));
        }).orElseGet(() -> {
            author.setAuthor_id(id);
            return Mapper.mapFromAuthorToDto(authorRepository.save(author));
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
}
