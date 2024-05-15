package com.tgsbhadohi.TGS.entities.student;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tgsbhadohi.TGS.entities.fees.Fees;
import com.tgsbhadohi.TGS.entities.fees.StudentFeesInstallment;
import com.tgsbhadohi.TGS.entities.fees.StudentFeesStructure;
import com.tgsbhadohi.TGS.entities.masters.AcademicYear;
import com.tgsbhadohi.TGS.entities.masters.Standard;
import com.tgsbhadohi.TGS.entities.masters.UploadedDocuments;
import com.tgsbhadohi.TGS.entities.masters.UploadedProfileImage;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.SecondaryTable;
import jakarta.persistence.SecondaryTables;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
//@SecondaryTables(value = {
//        @SecondaryTable(name = "Standard", pkJoinColumns = @PrimaryKeyJoinColumn(name = "col2", referencedColumnName = "classCode")),
//        @SecondaryTable(name = "AcademicYear", pkJoinColumns = @PrimaryKeyJoinColumn(name = "col3", referencedColumnName = "academicYearCode"))
//})
public class Registration {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long registrationId;
	
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
	
	private String dateOfAdmission;
	
	//@NotBlank(message="Standard Can't be blank")
	private String standard;   
	
	//@NotBlank(message="Section Can't be blank")
	private String section;   
	
//	@NotBlank(message="Academic Year Can't be blank")
//	@OneToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name="academicYearCode", referencedColumnName = "academicYearCode")
//	private AcademicYear academicYear;
	private String academicYearCode; 
	
	//@Size(min=12, max=12, message="Length of Aadhar Number must be 12 character")
	//@Column(nullable = true, unique = true)
	private String aadhaarNumber;
	
	//@NotBlank(message="Religion Can't be blank")
	private String religion;  
	private String bloodGroup;
	
	//@NotBlank(message="Category Can't be blank")
	private String category;   
	
	//@NotBlank(message="Registration Number Can't be blank")
	//@Column(unique = true)
	private String registrationNo;
	
	private String enrollmentType;
	
	private String idCardNumber;
	
	private Boolean isPromoted;
	
	private Boolean isActive;
	
	private Boolean isChecked;
	
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
	
	private String temp;
	
	private double totalFees;
    private double paidFees;
    private double pendingFees;
    private double discountAmount;
    private Boolean isTotalFeesPaid;
    
    private double bookFees;
    private double pendingBookFees;
    private double paidBookFees;
    private Boolean isTotalBookFeesPaid;
    
    @CreationTimestamp
	private Date createdOn;
	@UpdateTimestamp
	private Date lastUpdatedOn;
	
	
	@JsonManagedReference
	@OneToOne(mappedBy="registrationId" , cascade = CascadeType.ALL)
//    @OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "registrationId", referencedColumnName = "registrationId")
	private UploadedProfileImage profileImage;
	
	@JsonManagedReference
	@OneToMany(mappedBy="registrationId" , cascade = CascadeType.ALL)
	private List<UploadedDocuments> documents;
	
	
	@JsonManagedReference
	@OneToMany(mappedBy="registrationId" , cascade = CascadeType.ALL)
	private List<StudentFeesStructure> studentFeesStructure;
	
	@JsonManagedReference
	@OneToMany(mappedBy="registrationId" , cascade = CascadeType.ALL)
	private List<Fees> fees;
	
	
	
//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name = "registrationId", referencedColumnName = "registrationId")
//	private List<StudentFeesStructure> studentFeesStructure;
	
//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name = "registrationId", referencedColumnName = "registrationId")
//	private List<Fees> fees;
	
}
