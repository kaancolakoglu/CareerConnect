package com.springframework.CareerConnect.model;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class JobPostingDTO {
    private Long jobId;
    private String jobTitle;
    private String jobDescription;
    private String jobLocation;
    private List<String> tags;
    private List<String> applicantNames;
}