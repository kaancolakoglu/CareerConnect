package com.springframework.CareerConnect.model;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EducationDTO {
    private Long educationId;
    private String schoolName;
    private String degree;
    private String major;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
