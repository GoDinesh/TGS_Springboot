package com.tgsbhadohi.TGS.entities.masters;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Installment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String classCode;
	private String academicYearCode;
	private String installmentNumber;
	private String installmentDate;
	private Integer installmentAmount;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn( name="feeStructureId", referencedColumnName="feeStructureId")
	private FeesStructure feeStructureId;
}
