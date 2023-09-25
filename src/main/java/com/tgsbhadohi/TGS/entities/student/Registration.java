package com.tgsbhadohi.TGS.entities.student;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tgsbhadohi.TGS.entities.masters.UploadedDocuments;
import com.tgsbhadohi.TGS.entities.masters.UploadedProfileImage;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
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
public class Registration {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private int rollNumber;
	//Init
	//Student details
	//@Size(min=3, max=50, message="Length Student Name must be 3 - 50 character")
	//@NotBlank(message="Student Name Code Can't be blank")
	private String studentName;
	
	//@NotBlank(message="Gender Code Can't be blank")
	private String gender;   
	
	//@NotBlank(message="Birth Date Can't be blank")
	private String dateOfBirth; 
	
	//@NotBlank(message="Standard Can't be blank")
	private String standard;   
	
	//@NotBlank(message="Section Can't be blank")
	private String section;   
	
	//@NotBlank(message="Academic Year Can't be blank")
	private String academicYearCode; 
	
	//@Size(min=12, max=12, message="Length of Aadhar Number must be 12 character")
	//@Column(nullable = true, unique = true)
	private String aadhaarNumber;
	
	//@NotBlank(message="Religion Can't be blank")
	private String religion;   
	
	//@NotBlank(message="Category Can't be blank")
	private String category;   
	
	//@NotBlank(message="Registration Number Can't be blank")
	@Column(unique = true)
	private String registrationNo;
	
	//Parent Info
	//@Size(min=3, max=50, message="Father Name must be 3 - 50 character")
	//@NotBlank(message="Father Name Can't be blank")
	private String fatherName; 
	
	//@Size(min=12, max=12, message="Father Aadhar Number must be 12 character")
	//@Column(nullable = true, unique = true)
	private String fatherAadharNo;
	
	//@Size(min=10, max=10, message="Father Contact Number must be 10 character")
	private String fatherContactNo; 
	
	//@Size(min=1, max=50, message="Father Qualification must be 1 - 50 character")
	private String fatherQualification; 
	
	//@Size(min=1, max=50, message="Father Profession must be 1 - 50 character")
	private String fatherProfession; 
	
	//@Email(message= "Email is not valid")
	private String fatherEmailId; 
	
	//@NotBlank(message="Mother Name Code Can't be blank")
	//@Size(min=3, max=50, message="Mother Name must be 3 - 50 character")
	private String motherName; 
	
	//@Size(min=12, max=12, message="Mother Aadhar must be 12 character")
	//@Column(nullable = true, unique = true)
	private String motherAadharNumber; 
	
	//@Size(min=10, max=10, message="Mother contact must be 10 character")
	private String  motherContactNumber;
	
	//@Size(min=1, max=50, message="Mother profession must be 1 - 50 character")
	private String motherProfession;
	
	//@Size(min=3, max=50, message="Guardian name must be 3 - 50 character")
	private String guardianName;
	
	//Address Info
	private String country; 
	private String state; 
	
	//@Size(min=3, max=100, message="Length of city must be 1 - 100 character")
	private String city; 
	
	//@Size(min=6, max=6, message="Length of pincode must be 6 character")
	private String pincode; 
	
	//@Size(min=3, max=100, message="Length of area must be 3 - 100 character")
	private String area;
	
	//Emergency Contact
	//@Size(min=3, max=50, message="Length of Emergency Contact person must be 3 - 50 character")
	private String emergencyContactPerson; 
	
	//@Column(nullable = true, unique = true)
	private String emergencyNumber;
	
	//Previous School
	//@Size(min=2, max=50, message="Length of school name must be 2 - 50 character")
	private String schoolName; 
	
	//@Size(min=2, max=50, message="Length of TC Number must be 2 - 50 character")
	private String tcNumber;   
	
	//@Size(min=1, max=50, message="Length of passed class must be 1 - 50 character")
	private String passedClass; 
	
	//@Size(min=2, max=3, message="Length of passed class marks must be 2 - 3 character")
	private String passedClassMarks;
	
	//@Size(min=2, max=100, message="Length of school address must be 2 - 100 character")
	private String schoolAddress;
	
	@JsonManagedReference
	@OneToOne(mappedBy="userRegistrationNo" , cascade = CascadeType.ALL)
	private UploadedProfileImage profileImage;
	
//	@OneToMany(mappedBy="registrationNo")
//	private Set<UploadedDocuments> documents;
	
	@JsonManagedReference
	@OneToMany(mappedBy="userRegistrationNo" , cascade = CascadeType.ALL)
	private List<UploadedDocuments> documents;
}
