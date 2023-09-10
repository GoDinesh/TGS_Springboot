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
public class DiscountReason {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Size(min=3, max=50, message="Length of Discount Reason must be 3 - 8 character")
	@NotBlank(message="Discount Reason Can't be blank")
	@Column(nullable = false, unique = true)
	private String discountReason;
	
	private String active;

}
