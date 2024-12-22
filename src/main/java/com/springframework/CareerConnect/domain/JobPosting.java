package com.springframework.CareerConnect.domain;

import com.springframework.CareerConnect.enums.JobPostingStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "jobPosting")
public class JobPosting{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long jobId;

    private String jobTitle;
    @Column(length = 1000)
    private String jobDescription;
    private String jobLocation;

    private LocalDateTime deadline;

    private JobPostingStatus jobPostingStatus;

    private String sector;

    @ManyToMany
    @JoinTable(name = "job_posting_tags",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tag;


    @ManyToMany
    @JoinTable(name = "job_applicants",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "profile_id"))
    private Set<User> applicant;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "jobPosting", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Application> applications = new ArrayList<>();
}
