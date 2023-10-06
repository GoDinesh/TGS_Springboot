package com.tgsbhadohi.TGS.entities.masters;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	private Long id;
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
    
    private String installmentNo1;
    private String installmentDate1;
    private double installmentAmount1;
    
    private String installmentNo2;
    private String installmentDate2;
    private double installmentAmount2;
    
    private String installmentNo3;
    private String installmentDate3;
    private double installmentAmount3;
    
    private String installmentNo4;
    private String installmentDate4;
    private double installmentAmount4;
    
    private String installmentNo5;
    private String installmentDate5;
    private double installmentAmount5;
    
    private String installmentNo6;
    private String installmentDate6;
    private double installmentAmount6;
}
