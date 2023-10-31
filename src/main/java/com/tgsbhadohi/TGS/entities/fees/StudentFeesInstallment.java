package com.tgsbhadohi.TGS.entities.fees;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.tgsbhadohi.TGS.entities.masters.FeesStructure;
import com.tgsbhadohi.TGS.entities.masters.Installment;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class StudentFeesInstallment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String classCode;
	private String academicYearCode;
	private String installmentNumber;
	private String installmentDate;
	private Integer installmentAmount;
	private String discountReason;
	private Integer discountAmount;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn( name="studentFeeStructureId", referencedColumnName="studentFeeStructureId")
	private StudentFeesStructure studentFeeStructureId;
}
