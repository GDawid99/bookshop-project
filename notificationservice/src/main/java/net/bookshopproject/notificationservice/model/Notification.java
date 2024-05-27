package net.bookshopproject.notificationservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@Setter
@ToString
@RedisHash("notification")
public class Notification {
    @Id
    private String id;
    private String cartId;
    @Indexed
    private String userId;
    private String title;
    private String body;

    public Notification(String cartId, String userId, String title, String body) {
        this.cartId = cartId;
        this.userId = userId;
        this.title = title;
        this.body = body;
    }


}
