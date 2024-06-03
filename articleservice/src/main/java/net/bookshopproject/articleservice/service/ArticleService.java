package net.bookshopproject.articleservice.service;

import net.bookshopproject.articleservice.dto.ArticleDto;
import net.bookshopproject.articleservice.mapper.Mapper;
import net.bookshopproject.articleservice.model.Article;
import net.bookshopproject.articleservice.model.User;
import net.bookshopproject.articleservice.repository.ArticleRepository;
import net.bookshopproject.articleservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    UserRepository userRepository;

    public ArticleDto createNewArticle(ArticleDto articleDto, long user_id) {
        User user = userRepository.findById(user_id).orElseThrow();
        articleDto.setUser(Mapper.mapFromUserToDto(user));
        articleDto.setDateOfPublication(LocalDate.now());
        return Mapper.mapFromArticleToDto(articleRepository.save(Mapper.mapFromDtoToArticle(articleDto)));
    }

    public Page<ArticleDto> getAllArticleWithPageable(int page, int size) {
        Page<Article> articles = articleRepository.findAll(PageRequest.of(page,size, Sort.by(Sort.Direction.DESC,"dateOfPublication")));
        return articles.map(Mapper::mapFromArticleToDto);
    }

    public ArticleDto getArticle(long id) {
        Article article = articleRepository.findById(id).orElseThrow();
        return Mapper.mapFromArticleToDto(article);
    }

    public ArticleDto updateArticle(ArticleDto articleDto) {
        return Mapper.mapFromArticleToDto(articleRepository.save(Mapper.mapFromDtoToArticle(articleDto)));
    }

    public String deleteArticle(ArticleDto articleDto) {
        return "";
    }

}
