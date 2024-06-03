package net.bookshopproject.articleservice.repository;

import net.bookshopproject.articleservice.model.Article;
import net.bookshopproject.articleservice.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByArticle(Article article);
}
