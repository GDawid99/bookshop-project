package net.bookshopproject.articleservice.service;

import net.bookshopproject.articleservice.dto.ArticleDto;
import net.bookshopproject.articleservice.mapper.Mapper;
import net.bookshopproject.articleservice.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    public ArticleDto createNewArticle(ArticleDto articleDto) {
        return Mapper.mapFromArticleToDto(articleRepository.save(Mapper.mapFromDtoToArticle(articleDto)));
    }

}
