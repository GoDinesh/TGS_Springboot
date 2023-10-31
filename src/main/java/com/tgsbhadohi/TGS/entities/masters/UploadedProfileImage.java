package com.tgsbhadohi.TGS.entities.masters;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.tgsbhadohi.TGS.entities.student.Registration;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
public class UploadedProfileImage {

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private long id;
	
	@Id
	@JsonBackReference
//	@OneToOne(cascade = CascadeType.ALL)
	@OneToOne
	@JoinColumn( name="registrationNo", referencedColumnName="registrationNo")
	private Registration userRegistrationNo;
	
	private String fileName;	
	
	private String link;	
}
