package com.tgsbhadohi.TGS.entities.masters;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Syllabus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long syllabusId;

    private String standard;

    private String academicYearCode;
    
    private boolean active;
	

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "syllabus_id")
    private List<SyllabusSubjectDetails> syllabusSubjectDetails = new ArrayList<>();
}