package net.bookshopproject.bookservice.controller;

import net.bookshopproject.bookservice.dto.BookDto;
import net.bookshopproject.bookservice.model.Book;
import net.bookshopproject.bookservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/page")
    public ResponseEntity<List<BookDto>> getBooksByPage(@RequestParam("title") String title, @RequestParam(name = "id") long id, @RequestParam(name="size") int size) {
        return ResponseEntity.ok(bookService.findBooksByPage(title,id,size));
    }

    @GetMapping()
    public ResponseEntity<List<BookDto>> getAllBooks() {
        return ResponseEntity.ok(bookService.findAllBooks());
    }

    @GetMapping("/{title}/{id}")
    public ResponseEntity<BookDto> getBookByTitle(@PathVariable String title, @PathVariable long id) {
        return ResponseEntity.ok(bookService.findBookByTitle(title,id));
    }

    @GetMapping("/random")
    public ResponseEntity<List<BookDto>> getThreeRandomBooks() {
        return ResponseEntity.ok(bookService.findThreeRandomBooks());
    }

    @PostMapping("/add/{author_id}")
    public ResponseEntity<BookDto> createNewBook(@RequestBody BookDto bookDto, @PathVariable long author_id) {
        BookDto tmpBookDto;
        try {
            tmpBookDto = bookService.createNewBook(bookDto,author_id);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tmpBookDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BookDto> updateBookById(@RequestBody BookDto bookDto, @PathVariable long id) {
        BookDto tmpBookDto;
        try {
            tmpBookDto = bookService.updateBookById(id, bookDto);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tmpBookDto);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable long id) {
        return ResponseEntity.ok(bookService.deleteBookById(id));
    }
}
