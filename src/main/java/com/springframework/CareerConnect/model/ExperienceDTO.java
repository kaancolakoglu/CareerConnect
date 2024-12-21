package com.springframework.CareerConnect.model;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ExperienceDTO {
    private Long experienceId;
    private String companyName;
    private String jobTitle;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String description;
}
