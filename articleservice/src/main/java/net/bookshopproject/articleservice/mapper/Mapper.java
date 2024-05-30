package net.bookshopproject.articleservice.mapper;

import net.bookshopproject.articleservice.dto.ArticleDto;
import net.bookshopproject.articleservice.dto.CommentDto;
import net.bookshopproject.articleservice.model.Article;
import net.bookshopproject.articleservice.model.Comment;

public class Mapper {
    public static Article mapFromDtoToArticle(ArticleDto articleDto) {
        Article article = new Article();
        article.setArticle_id(articleDto.getArticle_id());
        article.setBody(articleDto.getBody());
        article.setTitle(articleDto.getTitle());
        article.setCommentList(articleDto.getCommentList());
        article.setDateOfPublication(articleDto.getDateOfPublication());
        return article;
    }

    public static ArticleDto mapFromArticleToDto(Article article) {
        ArticleDto articleDto = new ArticleDto();
        articleDto.setArticle_id(article.getArticle_id());
        articleDto.setBody(article.getBody());
        articleDto.setTitle(article.getTitle());
        articleDto.setCommentList(article.getCommentList());
        articleDto.setDateOfPublication(article.getDateOfPublication());
        return articleDto;
    }

    public static Comment mapFromDtoToComment(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setComment_id(commentDto.getComment_id());
        comment.setUser_id(commentDto.getUser_id());
        comment.setBody(commentDto.getBody());
        comment.setArticle(commentDto.getArticle());
        comment.setDateOfPublication(commentDto.getDateOfPublication());
        return comment;
    }

    public static CommentDto mapFromCommentToDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setComment_id(comment.getComment_id());
        commentDto.setUser_id(comment.getUser_id());
        commentDto.setBody(comment.getBody());
        commentDto.setArticle(comment.getArticle());
        commentDto.setDateOfPublication(comment.getDateOfPublication());
        return commentDto;
    }

}
