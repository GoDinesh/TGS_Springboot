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

public class BookDressFees {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private long id;
	//@Column(unique = true)
	private String standard;
	//@Column(unique = true)
	private String academicYearCode;
	private Integer bookFees;
//	private Integer boyDressFees;
//	private Integer girlDressFees;
	private boolean active;
}
