package net.bookshopproject.notificationservice.service;

import net.bookshopproject.notificationservice.config.NotificationMessageConfig;
import net.bookshopproject.notificationservice.model.Notification;
import net.bookshopproject.notificationservice.repository.NotificationRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    NotificationRepository notificationRepository;

    @RabbitListener(queues = NotificationMessageConfig.ORDER_STATUS_QUEUE)
    public void createNotificationAboutChangingStatus(String msg) {
        System.out.println(msg);
        String[] vars = splitMessage(msg);
        String status = vars[0];
        String cart_id = vars[1];
        String user_id = vars[2];

        String title = "Zmiana statusu";
        String body = "Status twojego zamówienia został zmieniony na " + status + ".";

        Notification notification = new Notification(cart_id,user_id,title,body);
        notificationRepository.save(notification);

        System.out.println(notification.toString());

    }

    public List<Notification> getNotificationByUserId(String user_id) {
        return notificationRepository.findByUserId(user_id);
    }

    public Iterable<Notification> getNotifications() {
        return notificationRepository.findAll();
    }

    public String removeNotificationById(String id) {
        notificationRepository.deleteById(id);
        return "Usunieto.";
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
