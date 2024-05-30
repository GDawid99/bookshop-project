package net.bookshopproject.articleservice.controller;

import net.bookshopproject.articleservice.dto.ArticleDto;
import net.bookshopproject.articleservice.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @PostMapping("/create")
    public ArticleDto createArticle(@RequestBody ArticleDto articleDto) {
        return articleService.createNewArticle(articleDto);
    }

}
