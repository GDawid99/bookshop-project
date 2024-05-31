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

    @PostMapping("/create")
    public ArticleDto createArticle(@RequestBody ArticleDto articleDto) {
        return articleService.createNewArticle(articleDto);
    }

    @GetMapping("/")
    public Page<ArticleDto> getArticles(@RequestParam int page, @RequestParam int size) {
        return articleService.getAllArticleWithPageable(page,size);
    }

}
