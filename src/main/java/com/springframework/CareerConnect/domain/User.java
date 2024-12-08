package com.springframework.CareerConnect.domain;

import com.springframework.CareerConnect.enums.ERole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users") //Because user was a reserved keyword in MSSQL
public class User extends BaseUser {

    @NotBlank
    private String name;

    @NotBlank
    private String lastName;

    private String phone;


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
        super(username, email, password);
    }

    @Builder
    public User(Long profileId, String username, String password, LocalDateTime createdDate, LocalDateTime updatedDate, LocalDateTime lastLoginDate, String status, ERole role, String name, String lastName, String email, String phone, Set<Role> roles, Set<Address> address, Set<JobPosting> savedJobPosting, Set<Skill> skill, Set<School> school, Company company) {
        super(profileId, username,email, password, createdDate, updatedDate, lastLoginDate, status, role);
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.roles = roles;
        this.address = address;
        this.savedJobPosting = savedJobPosting;
        this.skill = skill;
        this.school = school;
        this.company = company;
    }



}
