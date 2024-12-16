package com.springframework.CareerConnect.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicationDTO {
    private Long id;
    private Long jobId;
    private Long userId;
    private String status;
    private LocalDateTime submissionDate;
}
