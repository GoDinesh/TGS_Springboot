package com.tgsbhadohi.TGS.entities.masters;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SyllabusSubjectDetails {
		
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long subjectDetailsId;

	    private String subject;

	    @Column(columnDefinition = "LONGTEXT")
	    private String fa1;

	    @Column(columnDefinition = "LONGTEXT")
	    private String fa2;

	    @Column(columnDefinition = "LONGTEXT")
	    private String sa1;

	    @Column(columnDefinition = "LONGTEXT")
	    private String fa3;

	    @Column(columnDefinition = "LONGTEXT")
	    private String fa4;

	    @Column(columnDefinition = "LONGTEXT")
	    private String sa2;
}
