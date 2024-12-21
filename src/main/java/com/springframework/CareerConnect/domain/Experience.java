package com.springframework.CareerConnect.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "experience")
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long experienceId;

    private String companyName;

    private String jobTitle;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private String description;

    @ManyToOne
    @JoinColumn(name = "resume_id")
    private Resume resume;
}
