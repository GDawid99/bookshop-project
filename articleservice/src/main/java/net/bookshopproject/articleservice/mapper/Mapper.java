package net.bookshopproject.articleservice.mapper;

import net.bookshopproject.articleservice.dto.ArticleDto;
import net.bookshopproject.articleservice.dto.CommentDto;
import net.bookshopproject.articleservice.dto.UserDto;
import net.bookshopproject.articleservice.model.Article;
import net.bookshopproject.articleservice.model.Comment;
import net.bookshopproject.articleservice.model.User;

public class Mapper {
    public static Article mapFromDtoToArticle(ArticleDto articleDto) {
        Article article = new Article();
        article.setArticle_id(articleDto.getArticle_id());
        article.setBody(articleDto.getBody());
        article.setTitle(articleDto.getTitle());
        //article.setCommentList(articleDto.getCommentList());
        article.setDateOfPublication(articleDto.getDateOfPublication());
        article.setUser(mapFromDtoToUser(articleDto.getUser()));
        return article;
    }

    public static ArticleDto mapFromArticleToDto(Article article) {
        ArticleDto articleDto = new ArticleDto();
        articleDto.setArticle_id(article.getArticle_id());
        articleDto.setBody(article.getBody());
        articleDto.setTitle(article.getTitle());
        //articleDto.setCommentList(article.getCommentList());
        articleDto.setDateOfPublication(article.getDateOfPublication());
        articleDto.setUser(mapFromUserToDto(article.getUser()));
        return articleDto;
    }

    public static Comment mapFromDtoToComment(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setComment_id(commentDto.getComment_id());
        comment.setUser(mapFromDtoToUser(commentDto.getUser()));
        comment.setBody(commentDto.getBody());
        comment.setArticle(mapFromDtoToArticle(commentDto.getArticle()));
        comment.setDateOfPublication(commentDto.getDateOfPublication());
        return comment;
    }

    public static CommentDto mapFromCommentToDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setComment_id(comment.getComment_id());
        commentDto.setUser(mapFromUserToDto(comment.getUser()));
        commentDto.setBody(comment.getBody());
        commentDto.setArticle(mapFromArticleToDto(comment.getArticle()));
        commentDto.setDateOfPublication(comment.getDateOfPublication());
        return commentDto;
    }

    public static User mapFromDtoToUser(UserDto userDto) {
        User user = new User();
        user.setUserId(userDto.getUserId());
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        return user;
    }

    public static UserDto mapFromUserToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setFirstname(user.getFirstname());
        userDto.setLastname(user.getLastname());
        return userDto;
    }

}
