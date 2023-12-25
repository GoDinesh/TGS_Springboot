package com.tgsbhadohi.TGS.entities.masters;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Standard {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY) 
//	private long id; 
	
	@Id
	@NotBlank(message="Class Code Can't be blank")
	@Size(min = 2,max = 5, message="Length of Class Code is 3 - 5")
	//@Column(nullable = false, unique = true)
	private String classCode;
	
	@NotBlank(message="Class Name Can't be blank")
	@Size(min = 2,max = 50 , message="Length of Class Name is 2 - 50")
	private String className;
	
	private boolean active;
	
		
}
