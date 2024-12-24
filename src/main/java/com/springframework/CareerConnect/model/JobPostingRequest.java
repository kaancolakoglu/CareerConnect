package com.springframework.CareerConnect.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class JobPostingRequest {
    private Long companyId;
    private String jobTitle;
    private String jobDescription;
    private String jobLocation;
    private LocalDateTime deadline;
    private Set<String> tag;
}
