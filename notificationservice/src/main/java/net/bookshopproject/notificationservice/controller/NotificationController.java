package net.bookshopproject.notificationservice.controller;

import net.bookshopproject.notificationservice.model.Notification;
import net.bookshopproject.notificationservice.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @GetMapping("/all")
    public ResponseEntity<Iterable<Notification>> getAllNotifications() {
        return ResponseEntity.ok(notificationService.getNotifications());
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<List<Notification>> getAllNotificationByUser(@PathVariable String user_id) {
        List<Notification> notifications =notificationService.getNotificationByUserId(user_id);
        System.out.println(notifications);
        return ResponseEntity.ok(notifications);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> deleteNotification(@PathVariable String id) {
        return ResponseEntity.ok(notificationService.removeNotificationById(id));
    }

}
