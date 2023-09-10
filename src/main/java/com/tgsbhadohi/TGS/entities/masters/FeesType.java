package com.tgsbhadohi.TGS.entities.masters;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
public class FeesType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Size(min=2, max=50, message="Length of Fees Type must be 2 - 50 character")
	@NotBlank(message="Fees Type Can't be blank")
	@Column(nullable = false, unique = true)
	private String feesType;
	
	@Size(min=5, max=100, message="Length of Fees Type Description must be 2 - 50 character")
	@NotBlank(message="Fees Type Description Can't be blank")
	private String feesTypeDesc;
	
	private boolean active;

}
