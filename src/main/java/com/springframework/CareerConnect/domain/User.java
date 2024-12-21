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

    @ManyToMany
    @JoinTable(name = "user_role",
    joinColumns = @JoinColumn(name = "profile_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"),
            foreignKey = @ForeignKey(name = "FK_USER_ROLE"),
            inverseForeignKey = @ForeignKey(name = "FK_ROLE_USER")
    )
    private Set<Role> roles = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "user_address",
            joinColumns = @JoinColumn(name = "profile_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id"),
            foreignKey = @ForeignKey(name = "FK_USER_ADDRESS"),
            inverseForeignKey = @ForeignKey(name = "FK_ADDRESS_USER")
    )
    private Set<Address> address;


    //TODO: Draw that to ER diagram
    @ManyToMany
    @JoinTable(name = "saved_job_posting",
            joinColumns = @JoinColumn(name = "profile_id"),
            inverseJoinColumns = @JoinColumn(name = "job_id"))
    private Set<JobPosting> savedJobPosting;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Resume> resumes =  new HashSet<>();

    public User(String username, String email, String password) {
        super(username, email, password);
    }

    @Builder
    public User(Long profileId, String username, String phoneNumber, String password, LocalDateTime createdDate,
                LocalDateTime updatedDate, LocalDateTime lastLoginDate, String status,
                ERole role, String name, String lastName, String email, Set<Role> roles,
                Set<Address> address, Set<JobPosting> savedJobPosting, Company company, Set<Resume> resumes) {
        super(profileId, username, phoneNumber ,email, password, createdDate, updatedDate, lastLoginDate, status, role);
        this.name = name;
        this.lastName = lastName;
        this.roles = roles;
        this.address = address;
        this.savedJobPosting = savedJobPosting;
        this.company = company;
        this.resumes = resumes;
    }
}
