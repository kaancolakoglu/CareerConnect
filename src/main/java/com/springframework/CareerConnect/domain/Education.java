package com.springframework.CareerConnect.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long educationId;

    @ManyToMany(mappedBy = "education")
    private Set<School> school;

    private String educationName;

    private String degree;

    private String major;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

}
