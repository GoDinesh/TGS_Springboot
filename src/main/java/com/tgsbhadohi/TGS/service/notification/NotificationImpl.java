package com.tgsbhadohi.TGS.service.notification;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.tgsbhadohi.TGS.classes.NotificationHelper;
import com.tgsbhadohi.TGS.dao.notification.NotificationDao;
import com.tgsbhadohi.TGS.entities.notifications.NotificationMessage;


@Service
public class NotificationImpl implements NotificationService {

 @Autowired
 private NotificationDao notificationDao;
 
 @Autowired
 private NotificationHelper notificationHelper;
 
 @Autowired
 private SimpMessagingTemplate messagingTemplate;

 @Override
 public List<NotificationMessage> saveAll(List<NotificationMessage> notificationList) {

	    if (notificationList == null || notificationList.isEmpty()) {
	        throw new RuntimeException("Notification list is empty");
	    }

	    notificationList.forEach(notification -> {

	        notification.setNotificationId(null);

	        if (notification.getCreatedAt() == null) {
	            notification.setCreatedAt(LocalDateTime.now());
	        }

	        if (notification.getIsReaded() == null) {
	            notification.setIsReaded(false);
	        }
	    });

	    List<NotificationMessage> savedList = notificationDao.saveAll(notificationList);
	    // ================= SEND WEBSOCKET =================
	    savedList.forEach(notification -> {
	        messagingTemplate.convertAndSend("/topic/notification/" + notification.getRegistrationNo(), notification);
	    });

	    return savedList;
	}
// public NotificationMessage save(NotificationMessage notification) {
//     notification.setCreatedAt(LocalDateTime.now());
//     if (notification.getIsReaded() == false) {
//         notification.setIsReaded(false);
//     }
//     return notificationDao.save(notification);
// }

 @Override
 public List<NotificationMessage> getAll() {
     return notificationDao.findAll()
             .stream()
             .sorted((a, b) ->
                     b.getNotificationId()
                             .compareTo(a.getNotificationId()))
             .toList();
 }

 @Override
 public NotificationMessage getById(Long id) {
     return notificationDao.findById(id).orElse(null);
 }

 @Override
 public List<NotificationMessage> getByRegistrationNoAndAcademicYearCode(
         String registrationNo, String academicYearCode) {

     return notificationDao
             .findByRegistrationNoAndAcademicYearCodeOrderByNotificationIdDesc(
                     registrationNo, academicYearCode);
 }

 @Override
 public List<NotificationMessage> getUnreadNotifications() {

     return notificationDao
             .findByIsReadedFalseOrderByNotificationIdDesc();
 }

 @Override
 public NotificationMessage markAsRead(Long id) {
     NotificationMessage notification = getById(id);
     if (notification != null) {
         notification.setIsReaded(true);
         return notificationDao.save(notification);
     }
     return null;
 }
 
 public Long getUnreadNotificationCount( String registrationNo,  String academicYearCode ) {
     return notificationDao.countByRegistrationNoAndAcademicYearCodeAndIsReadedFalse( registrationNo, academicYearCode);
 }

 
}