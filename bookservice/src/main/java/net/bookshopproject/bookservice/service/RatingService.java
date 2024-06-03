package net.bookshopproject.bookservice.service;

import net.bookshopproject.bookservice.config.BrokerConfig;
import net.bookshopproject.bookservice.dto.BookDto;
import net.bookshopproject.bookservice.dto.RatingDto;
import net.bookshopproject.bookservice.mapper.Mapper;
import net.bookshopproject.bookservice.model.Book;
import net.bookshopproject.bookservice.model.Rating;
import net.bookshopproject.bookservice.repository.BookRepository;
import net.bookshopproject.bookservice.repository.RatingRepository;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class RatingService {
    @Autowired
    RatingRepository ratingRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookService bookService;

    @Autowired
    AmqpTemplate amqpTemplate;


    public List<RatingDto> getAllRatingsByBook(long book_id) {
        List<Rating> ratings = ratingRepository.findAllByBook(bookRepository.getReferenceById(book_id));
        List<RatingDto> dtos = new ArrayList<>();
        for (Rating r: ratings) {
            dtos.add(Mapper.mapFromRatingToDto(r));

        }
        return dtos;
    }

    public Boolean addNewRating(RatingDto ratingDto, long book_id) {
        Book book = bookRepository.getReferenceById(book_id);
        ratingDto.setBook(Mapper.mapFromBookToDto(book));
        Rating rating = Mapper.mapFromDtoToRating(ratingDto);
        if (ratingRepository.existsRatingByBookAndUserId(book,ratingDto.getUserId())) return false;
        rating.setBook(book);
        ratingRepository.save(rating);
        List<Rating> list = ratingRepository.findAllByBook(book);
        float avgRating = (float)list.stream().mapToDouble(Rating::getRating).sum()/list.size();
        book.setRating(avgRating);
        BookDto bookDto = Mapper.mapFromBookToDto(book);
        bookService.updateBookById(book_id,bookDto);
        return true;
    }
}
