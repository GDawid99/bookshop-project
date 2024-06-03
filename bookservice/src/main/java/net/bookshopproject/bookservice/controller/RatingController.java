package net.bookshopproject.bookservice.controller;

import net.bookshopproject.bookservice.dto.RatingDto;
import net.bookshopproject.bookservice.model.Rating;
import net.bookshopproject.bookservice.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/rating")
public class RatingController {
    @Autowired
    RatingService ratingService;

    @PostMapping("/add/{book_id}")
    public ResponseEntity<Boolean> createNewRating(@RequestBody RatingDto ratingDto, @PathVariable long book_id) {
        return ResponseEntity.ok(ratingService.addNewRating(ratingDto,book_id));
    }

    @GetMapping("/all/{book_id}")
    public ResponseEntity<List<RatingDto>> getAllRatingsByBook(@PathVariable long book_id) {
        return ResponseEntity.ok(ratingService.getAllRatingsByBook(book_id));
    }

}
