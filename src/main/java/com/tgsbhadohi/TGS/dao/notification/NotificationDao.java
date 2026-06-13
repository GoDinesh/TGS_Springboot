package com.tgsbhadohi.TGS.dao.notification;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tgsbhadohi.TGS.entities.notifications.NotificationMessage;

public interface NotificationDao extends JpaRepository<NotificationMessage, Long>{
	List<NotificationMessage> findByRegistrationNoAndAcademicYearCodeOrderByNotificationIdDesc(
            String registrationNo, String academicYearCode);

    List<NotificationMessage> findByIsReadedFalseOrderByNotificationIdDesc();
    long countByRegistrationNoAndAcademicYearCodeAndIsReadedFalse(
            String registrationNo,
            String academicYearCode
    );
}
