package net.bookshopproject.bookservice.controller;

import net.bookshopproject.bookservice.dto.AuthorDto;
import net.bookshopproject.bookservice.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/author")
public class AuthorController {
    @Autowired
    AuthorService authorService;

    @PostMapping("/add")
    public ResponseEntity<AuthorDto> createNewAuthor(@RequestBody AuthorDto authorDto) {
        return ResponseEntity.ok(authorService.addNewAuthor(authorDto));
    }

    @PostMapping("/get")
    public ResponseEntity<String> getAuthor(@RequestBody AuthorDto authorDto) {
        return ResponseEntity.ok(authorService.findAuthorByName(authorDto));
    }


    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> deleteAuthorById(@PathVariable long id) {
        return ResponseEntity.ok(authorService.deleteAuthorById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AuthorDto> updateAuthorById(@RequestBody AuthorDto authorDto, @PathVariable long id) {
        AuthorDto tmpAuthorDto;
        try {
            tmpAuthorDto = authorService.updateAuthorById(id, authorDto);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tmpAuthorDto);
    }
}
