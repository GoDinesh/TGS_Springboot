package com.tgsbhadohi.TGS.entities.masters;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class FeesStructure {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long feeStructureId;
	private String classCode;
    private String enrollmentType;
    private String academicYearCode;
    private String paymentType;
    private String validityStartDate;
    private String validityEndDate;
    private String remarks;
    private double totalFees;
    private double discountReasonCode;
    private double discountAmount;
    private double netAmountAfterDiscount;
    private double registrationFees;
    private double annualFees;
    private String annualFeesDate;	
    private boolean active;
    
    private double lumpsumAmount;
    
    @JsonManagedReference
	@OneToMany(mappedBy="feeStructureId" , cascade = CascadeType.ALL)   
    private List<Installment> installment;
    
    
}
