package com.springframework.CareerConnect.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "users") //Because user was a reserved keyword in MSSQL
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileId;

    //@NotBlank
    //@Size(max = 20)
    private String username;

    private String name;
    private String lastName;

    //@NotBlank
    //@Size(max = 50)
    @Email
    private String email;

    //@NotBlank
    //@Size(max = 120)
    private String password;
    private String status;
    private String phone;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;


    @ManyToMany
    @JoinTable(name = "user_role",
    joinColumns = @JoinColumn(name = "profile_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

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

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
