package com.tgsbhadohi.TGS.entities.masters;

import jakarta.persistence.Entity;
import java.util.List;
import jakarta.persistence.*;
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
public class TimeTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long timeTableId;

    private String standard;

    private String academicYearCode;
    
    private boolean active;
	
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "time_table_id")
    private List<TimeTableDetails> timeTableDetails;
}