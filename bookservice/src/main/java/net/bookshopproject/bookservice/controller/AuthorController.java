package net.bookshopproject.bookservice.controller;

import net.bookshopproject.bookservice.dto.AuthorDto;
import net.bookshopproject.bookservice.model.Author;
import net.bookshopproject.bookservice.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/author")
public class AuthorController {
    @Autowired
    AuthorService authorService;

    @PostMapping("/add")
    public Author createNewAuthor(@RequestBody AuthorDto authorDto) {
        return authorService.addNewAuthor(authorDto);
    }

    @DeleteMapping("/remove/{id}")
    public String deleteAuthorById(@PathVariable long id) {
        return authorService.deleteAuthorById(id);
    }

    @PutMapping("/update/{id}")
    public Author updateAuthorById(@RequestBody AuthorDto authorDto, @PathVariable long id) {
        return authorService.updateAuthorById(id,authorDto);
    }
}
