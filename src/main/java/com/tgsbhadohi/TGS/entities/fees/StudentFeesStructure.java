package com.tgsbhadohi.TGS.entities.fees;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tgsbhadohi.TGS.entities.masters.Installment;
import com.tgsbhadohi.TGS.entities.student.Registration;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
public class StudentFeesStructure {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long studentFeeStructureId;
	private String classCode;
    private String enrollmentType;
    private String academicYearCode;
    private String paymentType;
    private Integer totalFees=0;
    private Integer discountReasonCode;
    private Integer discountAmount=0;
    private Integer netAmountAfterDiscount=0;
//    private double registrationFees;
//    private double annualFees;
//    private String annualFeesDate;	
    //private Integer regFeesDiscount=0;
    //private String regFeesDiscountReason;
    private boolean active;
    
    private Integer lumpsumAmount=0;
    
    @CreationTimestamp
	private Date createdOn;
	@UpdateTimestamp
	private Date lastUpdatedOn;
	
    

    @JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="registrationId", referencedColumnName="registrationId", updatable = false)
	private Registration registrationId;
    
//    @JsonManagedReference
//	@OneToMany(mappedBy="studentFeeStructureId" , cascade = CascadeType.ALL, orphanRemoval = true)   
//    private List<StudentFeesInstallment> studentFeesInstallment;
    
//    @OneToMany(cascade = CascadeType.DETACH, orphanRemoval = true)
//    @JoinColumn(name = "studentFeeStructureId", referencedColumnName = "studentFeeStructureId")
    @JsonManagedReference
	@OneToMany(mappedBy="studentFeeStructureId" , cascade = CascadeType.ALL)
    private List<StudentFeesInstallment> studentFeesInstallment;
    
    
    
	
    
  
}
