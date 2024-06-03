package net.bookshopproject.bookservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BrokerConfig {
    public static final String BOOK_EXCHANGE = "book-exchange";
    public static final String BOOK_USER_QUEUE = "book-user-queue";

    public static final String KEY_BOOKUSER = "key.bookuser";

    @Bean
    public DirectExchange articleExchange() {
        return new DirectExchange(BOOK_EXCHANGE);
    }

    @Bean
    public Queue userQueue() {
        return new Queue(BOOK_USER_QUEUE);
    }

    @Bean
    public Binding userBinding() {
        return BindingBuilder.bind(userQueue()).to(articleExchange()).with(KEY_BOOKUSER);
    }
}
