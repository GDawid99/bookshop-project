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

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/page")
    public List<Book> getBooksByPage(@RequestParam("title") String title, @RequestParam(name = "id") long id, @RequestParam(name="size") int size) {
        return bookService.findBooksByPage(title,id,size);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping()
    public ResponseEntity<Object> getAllBooks() {
        return bookService.findAllBooks();
    }


    @GetMapping("/{title}/{id}")
    public Book getBookByTitle(@PathVariable String title, @PathVariable long id) {
        return bookService.findBookByTitle(title,id);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/random")
    public ResponseEntity<Object> getThreeRandomBooks() {
        return bookService.findThreeRandomBooks();
    }

    @PostMapping("/add/{author_id}")
    public Book createNewBook(@RequestBody BookDto bookDto, @PathVariable long author_id) {
        return bookService.createNewBook(bookDto,author_id);
    }

    @PutMapping("/update/{id}")
    public Book updateBookById(@RequestBody BookDto bookDto, @PathVariable long id) {
        return bookService.updateBookById(id, bookDto);
    }

    @DeleteMapping("/remove/{id}")
    public String deleteBookById(@PathVariable long id) {
        bookService.deleteBookById(id);
        return "Książka " + id + " została usunięta.";
    }
}
