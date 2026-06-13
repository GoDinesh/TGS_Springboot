package com.tgsbhadohi.TGS.entities.masters;

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
public class TimeTableDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long timeTableDetailsId;

    private String day;

    private String subject1;

    private String subject2;

    private String subject3;

    private String subject4;

    private String subject5;

    private String subject6;

    private String subject7;

    private String subject8;
}