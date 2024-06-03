package net.bookshopproject.articleservice.controller;

import net.bookshopproject.articleservice.dto.CommentDto;
import net.bookshopproject.articleservice.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping("/all/{id}")
    public ResponseEntity<List<CommentDto>> getCommentsByArticleId(@PathVariable long id) {
        return ResponseEntity.ok(commentService.getAllCommentByArticle(id));
    }

    @PostMapping("/create/{article_id}/{user_id}")
    public ResponseEntity<CommentDto> createNewComment(@RequestBody CommentDto commentDto, @PathVariable long article_id, @PathVariable long user_id) {
        return ResponseEntity.ok(commentService.createNewComment(commentDto,article_id,user_id));
    }
}
