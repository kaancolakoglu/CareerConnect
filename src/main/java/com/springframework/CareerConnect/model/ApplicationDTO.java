package com.springframework.CareerConnect.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicationDTO {
    private Long applicationId;
    private Long jobId;
    private Long userId;
    private String applicationStatus;

    private LocalDateTime submissionDate;
}
