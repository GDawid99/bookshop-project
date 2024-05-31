package net.bookshopproject.articleservice.service;

import net.bookshopproject.articleservice.dto.ArticleDto;
import net.bookshopproject.articleservice.mapper.Mapper;
import net.bookshopproject.articleservice.model.Article;
import net.bookshopproject.articleservice.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    public ArticleDto createNewArticle(ArticleDto articleDto) {
        return Mapper.mapFromArticleToDto(articleRepository.save(Mapper.mapFromDtoToArticle(articleDto)));
    }

    public Page<ArticleDto> getAllArticleWithPageable(int page, int size) {
        Page<Article> articles = articleRepository.findAll(PageRequest.of(page,size, Sort.by(Sort.Direction.DESC,"dateOfPublication")));
        return articles.map(Mapper::mapFromArticleToDto);
    }

    public ArticleDto updateArticle(ArticleDto articleDto) {
        return Mapper.mapFromArticleToDto(articleRepository.save(Mapper.mapFromDtoToArticle(articleDto)));
    }

    public String deleteArticle(ArticleDto articleDto) {
        return "";
    }

}
