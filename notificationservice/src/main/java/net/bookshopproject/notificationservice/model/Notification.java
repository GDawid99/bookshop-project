package net.bookshopproject.notificationservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@Setter
@ToString
@RedisHash(value = "notification", timeToLive = 15L)
public class Notification {
    @Id
    @Indexed
    private String id;
    private String cartId;
    @Indexed
    private String userId;
    private String title;
    private String body;
    @TimeToLive
    private Long expiration;

    public Notification(String cartId, String userId, String title, String body) {
        this.cartId = cartId;
        this.userId = userId;
        this.title = title;
        this.body = body;
    }


}
