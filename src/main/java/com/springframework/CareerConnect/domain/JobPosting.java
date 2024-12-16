package com.springframework.CareerConnect.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class JobPosting{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long jobId;

    private String jobTitle;
    @Column(length = 1000)
    private String jobDescription;
    private String jobLocation;

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
    private List<Application> applications;
}
