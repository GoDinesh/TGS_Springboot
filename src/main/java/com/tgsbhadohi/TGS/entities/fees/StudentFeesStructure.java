package com.tgsbhadohi.TGS.entities.fees;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tgsbhadohi.TGS.entities.masters.Installment;
import com.tgsbhadohi.TGS.entities.student.Registration;

import jakarta.persistence.CascadeType;
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
    private double totalFees;
    private double discountReasonCode;
    private double discountAmount;
    private double netAmountAfterDiscount;
    private double registrationFees;
    private double annualFees;
    private String annualFeesDate;	
    private double regFeesDiscount;
    private String regFeesDiscountReason;
    private boolean active;
    
    private double lumpsumAmount;
    
//    @JsonBackReference
//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name="registrationNo", referencedColumnName="registrationNo")
//	private Registration userRegistrationNo;
    
//    @JsonManagedReference
//	@OneToMany(mappedBy="studentFeeStructureId" , cascade = CascadeType.ALL, orphanRemoval = true)   
//    private List<StudentFeesInstallment> studentFeesInstallment;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "studentFeeStructureId", referencedColumnName = "studentFeeStructureId")
    private List<StudentFeesInstallment> studentFeesInstallment;
  
}
