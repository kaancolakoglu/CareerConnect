package com.springframework.CareerConnect.model;

import lombok.*;

import java.util.List;
import java.util.Set;

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
    private Set<TagDTO> tag;
    private CompanyDTO company;
    private List<String> applicantNames;
}