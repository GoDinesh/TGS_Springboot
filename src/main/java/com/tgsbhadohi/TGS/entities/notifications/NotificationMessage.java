package com.tgsbhadohi.TGS.entities.notifications;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationMessage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long notificationId;
	
	private Boolean isReaded=false;

    private String notificationType;

    private String notificationTitle;

    private String notificationMessage;
    
    private LocalDateTime createdAt;
    
    private String studentName;
    private String standard; 
    private String academicYearCode;
    private String registrationNo;

    // date of notification
    @Convert(converter = MapToJsonConverter.class)
    @Column(columnDefinition = "LONGTEXT")
    private Map<String, Object> extraData;
}


