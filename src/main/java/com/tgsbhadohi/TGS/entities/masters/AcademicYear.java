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
public class AcademicYear {
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private long id;
	
	@Id
	@Size(min=8, max=8, message="Length of Academic Year Code must be 8 character")
	@NotBlank(message="Academic Year Code Can't be blank")
	//@Column(nullable = false, unique = true)
	private String academicYearCode;
	
	@Size(min=9, max=9, message="length of Academic Year must be 9 character")
	@NotBlank(message="Academic Year Can't be blank")
	@Column(nullable=false, unique = true)
	private String academicYear;
	
	private boolean active;
}
