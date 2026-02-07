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
public class Holiday {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String academicYearCode;
	private String name;
	private String startDate;
	private String endDate;
	private String type; 
	private boolean active;
}
