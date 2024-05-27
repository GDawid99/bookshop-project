package net.bookshopproject.notificationservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificationMessageConfig {
    public static final String NOTIFICATION_EXCHANGE = "notification-exchange";
    public static final String ORDER_STATUS_QUEUE = "order-status-queue";
    public static final String ARTICLE_COMMENT_RESPONSE_QUEUE = "article-comment-response-queue";

    public static final String KEY_ORDER = "key.order";
    public static final String KEY_ARTICLE = "key.article";

    @Bean
    public DirectExchange notificationDirectExchange() {
        return new DirectExchange(NOTIFICATION_EXCHANGE);
    }

    @Bean
    public Queue orderStatusQueue() {
        return new Queue(ORDER_STATUS_QUEUE);
    }

    @Bean
    public Queue articleStatusQueue() {
        return new Queue(ARTICLE_COMMENT_RESPONSE_QUEUE);
    }

    @Bean
    public Binding orderBinding() {
        return BindingBuilder.bind(orderStatusQueue()).to(notificationDirectExchange()).with(KEY_ORDER);
    }

    @Bean
    public Binding articleBinding() {
        return BindingBuilder.bind(articleStatusQueue()).to(notificationDirectExchange()).with(KEY_ARTICLE);
    }

}
