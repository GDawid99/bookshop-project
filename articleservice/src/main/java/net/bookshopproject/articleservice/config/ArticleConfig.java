package net.bookshopproject.articleservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArticleConfig {
    public static final String ARTICLE_EXCHANGE = "article-exchange";
    public static final String USER_QUEUE = "user-queue";

    public static final String KEY_USER = "key.user";

    @Bean
    public DirectExchange articleExchange() {
        return new DirectExchange(ARTICLE_EXCHANGE);
    }

    @Bean
    public Queue userQueue() {
        return new Queue(USER_QUEUE);
    }

    @Bean
    public Binding userBinding() {
        return BindingBuilder.bind(userQueue()).to(articleExchange()).with(KEY_USER);
    }
}
