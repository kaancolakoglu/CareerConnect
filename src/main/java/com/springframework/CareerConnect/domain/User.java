package com.springframework.CareerConnect.domain;

import jakarta.persistence.*;
import lombok.*;

import org.joda.time.LocalDateTime;
import java.util.Set;



@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "app_user") //Because user was a reserved keyword in MSSQL
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long profileId;

    private String username;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private String role;
    private String status;
    private String phone;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @ManyToMany
    @JoinTable(name = "user_address",
            joinColumns = @JoinColumn(name = "profile_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id"))
    private Set<Address> address;

    @ManyToMany
    @JoinTable(name = "saved_job_posting",
            joinColumns = @JoinColumn(name = "profile_id"),
            inverseJoinColumns = @JoinColumn(name = "job_id"))
    private Set<JobPosting> savedJobPosting;


    @ManyToMany
    @JoinTable(name = "user_skills",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private Set<Skill> skill;

    @ManyToMany
    @JoinTable(name = "user_schools",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "school_id")
        )
    private Set<School> school;


    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;


}
