package com.tgsbhadohi.TGS.controller.notification;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tgsbhadohi.TGS.entities.notifications.NotificationMessage;
import com.tgsbhadohi.TGS.service.notification.NotificationService;

@RestController
@RequestMapping("/notification")
@CrossOrigin("*")
public class NotificationController {


    @Autowired
    private NotificationService notificationService;

    // ================= SAVE =================
    @PostMapping("/insert")
    public ResponseEntity<?> save(
            @RequestBody List<NotificationMessage> notification) {
    	
    	try {

            List<NotificationMessage> savedNotifications =  notificationService.saveAll(notification);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    Map.of(
                            "success", true,
                            "message", "Notifications sent successfully",
                            "count", savedNotifications.size(),
                            "data", savedNotifications
                    )
            );

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    Map.of(
                            "success", false,
                            "message", e.getMessage()
                    )
            );
        }
    }

    // ================= GET ALL =================
    @PostMapping("/findall")
    public ResponseEntity<List<NotificationMessage>> getAll() {

        return new ResponseEntity<>(
                notificationService.getAll(),
                HttpStatus.OK);
    }

    // ================= GET BY ID =================
    @PostMapping("/find-by-id")
    public ResponseEntity<?> getById(
            @RequestBody NotificationMessage notification) {

        return new ResponseEntity<>(
                notificationService.getById(
                        notification.getNotificationId()),
                HttpStatus.OK);
    }

    // ================= GET BY REGISTRATION NO =================
    @PostMapping("/list")
    public ResponseEntity<?> getByRegistrationNo(@RequestBody NotificationMessage notification) {
    List<NotificationMessage>	notificationList = notificationService.getByRegistrationNoAndAcademicYearCode(
                notification.getRegistrationNo(), notification.getAcademicYearCode());
        return new ResponseEntity<>(notificationList,HttpStatus.OK);
    }

    // ================= GET UNREAD =================
    @PostMapping("/unread")
    public ResponseEntity<?> unreadNotifications() {

        return new ResponseEntity<>(
                notificationService.getUnreadNotifications(),
                HttpStatus.OK);
    }

    // ================= MARK AS READ =================
    @PostMapping("/mark-as-read")
    public ResponseEntity<?> markAsRead(@RequestBody Long notificationId) {

        return new ResponseEntity<>(
                notificationService.markAsRead(notificationId),
                HttpStatus.OK);
    }
    
    @PostMapping("/unread-count")
    public ResponseEntity<Long> getUnreadCount(	@RequestBody NotificationMessage notification) {
        long count = notificationService.getUnreadNotificationCount(notification.getRegistrationNo(),
        		notification.getAcademicYearCode());
        return ResponseEntity.ok(count);
    }
    
}
