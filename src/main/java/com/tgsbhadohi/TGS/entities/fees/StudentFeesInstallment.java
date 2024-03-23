package com.tgsbhadohi.TGS.entities.fees;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.tgsbhadohi.TGS.entities.masters.FeesStructure;
import com.tgsbhadohi.TGS.entities.masters.Installment;
import com.tgsbhadohi.TGS.entities.student.Registration;

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
	private String installmentType;
	private String discountReason;
	private Integer discountAmount=0;
	private String installmentDate;
	private Integer installmentDiscount=0;
	private Integer installmentAmount=0;
	private Integer installmentAmountAfterDiscount=0;
	
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn( name="studentFeeStructureId", referencedColumnName="studentFeeStructureId", updatable = false)
	private StudentFeesStructure studentFeeStructureId;
	
//	 @JsonBackReference
//		@ManyToOne(cascade = CascadeType.ALL)
//		@JoinColumn(name="registrationId", referencedColumnName="registrationId", nullable=false)
//		private Registration registrationId;
}
