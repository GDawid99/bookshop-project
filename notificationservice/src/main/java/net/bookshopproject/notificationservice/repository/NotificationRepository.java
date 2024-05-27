package net.bookshopproject.notificationservice.repository;

import net.bookshopproject.notificationservice.model.Notification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends CrudRepository<Notification,String> {
    List<Notification> findByUserId(String userId);


}
