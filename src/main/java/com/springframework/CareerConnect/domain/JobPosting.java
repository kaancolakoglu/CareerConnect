package com.springframework.CareerConnect.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Builder
public class JobPosting{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long jobId;

    private String jobTitle;
    private String jobDescription;
    private String jobLocation;
    private String jobType;
    private String jobCategory;


    @ManyToMany
    @JoinTable(name = "job_applicants",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "profile_id"))
    private Set<User> applicants;

    public JobPosting(Long jobId, String jobTitle, String jobDescription, String jobLocation, String jobType, String jobCategory, Set<User> applicants) {
        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.jobLocation = jobLocation;
        this.jobType = jobType;
        this.jobCategory = jobCategory;
        this.applicants = applicants != null ? applicants : new HashSet<>();
    }
}
