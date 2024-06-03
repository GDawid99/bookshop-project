package net.bookshopproject.articleservice.controller;

import net.bookshopproject.articleservice.dto.ArticleDto;
import net.bookshopproject.articleservice.service.ArticleService;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @PostMapping("/create/{user_id}")
    public ArticleDto createArticle(@RequestBody ArticleDto articleDto, @PathVariable long user_id) {
        return articleService.createNewArticle(articleDto, user_id);
    }

    @GetMapping("/")
    public Page<ArticleDto> getArticles(@RequestParam int page, @RequestParam int size) {
        return articleService.getAllArticleWithPageable(page,size);
    }

    @GetMapping("/{id}")
    public ArticleDto getArticle(@PathVariable long id) {
        return articleService.getArticle(id);
    }

}
