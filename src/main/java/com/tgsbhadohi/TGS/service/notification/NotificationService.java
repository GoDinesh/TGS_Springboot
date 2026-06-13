package com.tgsbhadohi.TGS.service.notification;

import java.util.List;

import com.tgsbhadohi.TGS.entities.notifications.NotificationMessage;

public interface NotificationService {
	List<NotificationMessage> saveAll(List<NotificationMessage> notification);

    List<NotificationMessage> getAll();

    NotificationMessage getById(Long id);

    List<NotificationMessage> getByRegistrationNoAndAcademicYearCode(String registrationNo, String academicYearCode);

    List<NotificationMessage> getUnreadNotifications();

    NotificationMessage markAsRead(Long id);
    
    Long getUnreadNotificationCount(String registrationNo, String academicYearCode);
   
}
