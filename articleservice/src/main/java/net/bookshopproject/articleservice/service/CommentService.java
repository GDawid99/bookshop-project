package net.bookshopproject.articleservice.service;

import net.bookshopproject.articleservice.config.ArticleConfig;
import net.bookshopproject.articleservice.dto.CommentDto;
import net.bookshopproject.articleservice.mapper.Mapper;
import net.bookshopproject.articleservice.model.Article;
import net.bookshopproject.articleservice.model.Comment;
import net.bookshopproject.articleservice.model.User;
import net.bookshopproject.articleservice.repository.ArticleRepository;
import net.bookshopproject.articleservice.repository.CommentRepository;
import net.bookshopproject.articleservice.repository.UserRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ArticleRepository articleRepository;

    public List<CommentDto> getAllCommentByArticle(long article_id) {
        Article article = articleRepository.findById(article_id).orElseThrow();
        List<Comment> list = commentRepository.findByArticle(article);
        List<CommentDto> dtoList = new ArrayList<>();
        for (Comment c : list) {
            c.setUser(userRepository.findById(c.getUser().getUserId()).orElseThrow());
            dtoList.add(Mapper.mapFromCommentToDto(c));
        }

        return dtoList;
    }

    public CommentDto createNewComment(CommentDto commentDto,long article_id, long user_id) {
        User user = userRepository.findById(user_id).orElseThrow();
        Article article = articleRepository.findById(article_id).orElseThrow();
        commentDto.setDateOfPublication(LocalDate.now());
        commentDto.setUser(Mapper.mapFromUserToDto(user));
        commentDto.setArticle(Mapper.mapFromArticleToDto(article));
        Comment comment = Mapper.mapFromDtoToComment(commentDto);
        System.out.println(commentDto.getComment_id());
        System.out.println(commentDto.getUser().getUserId());
        System.out.println(commentDto.getBody());
        System.out.println(commentDto.getArticle().getArticle_id());
        System.out.println(commentDto.getDateOfPublication());

        return Mapper.mapFromCommentToDto(commentRepository.save(comment));
    }


    @RabbitListener(queues = ArticleConfig.USER_QUEUE)
    public void createLocalUser(String msg) {
        String[] vars = splitMessage(msg);
        long user_id = Long.parseLong(vars[0]);
        String firstname = vars[1];
        String lastname = vars[2];



        User user = new User(user_id,firstname,lastname);
        if (!userRepository.existsById(user.getUserId())) userRepository.save(user);
    }

    private String[] splitMessage(String msg) {
        String[] tmp = msg.split(";");
        String[] vars = new String[tmp.length];
        int i = 0;
        for (String str : tmp) {
            String[] keyValue = str.split(":");
            vars[i] = keyValue[1];
            i++;
        }
        return vars;
    }
}
