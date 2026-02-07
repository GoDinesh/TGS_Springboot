package com.tgsbhadohi.TGS.entities.notifications;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Generates getters, setters, toString, equals, and hashCode
@AllArgsConstructor // Generates a constructor with all fields
@NoArgsConstructor  // Generates a no-argument constructor (optional)

public class FeesNotification {
	private String message;
    private String studentId; // optional
}
