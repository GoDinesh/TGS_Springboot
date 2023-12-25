package com.tgsbhadohi.TGS.entities.masters;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
	private long feeStructureId;
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
    private double regFeesDiscount;
    private String regFeesDiscountReason;
    private boolean active;
    
    private double lumpsumAmount;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "feeStructureId", referencedColumnName = "feeStructureId")
    private List<Installment> installment;
    
    
}
