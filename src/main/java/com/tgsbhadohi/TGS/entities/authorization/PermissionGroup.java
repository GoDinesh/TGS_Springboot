package com.tgsbhadohi.TGS.entities.authorization;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
public class PermissionGroup {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long groupid;
	
	@Column(nullable = false, unique = true)
	private String usergroup;
	
	private boolean active;
	
	@CreationTimestamp
	private Date createdOn;
	@UpdateTimestamp
	private Date lastUpdatedOn;
	
}
