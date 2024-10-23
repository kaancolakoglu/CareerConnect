package com.springframework.CareerConnect.domain;

import jakarta.persistence.*;
import lombok.*;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Entity
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


     public User(Long profileId, String username, String name, String lastName, String email, String password, String role, String status, String phone, LocalDateTime createdDate,LocalDateTime updatedDate, Set<Address> address, Set<JobPosting> savedJobPosting) {
        this.profileId = profileId;
        this.username = username;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.status = status;
        this.phone = phone;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.address = address = address != null ? address : new HashSet<>();
        this.savedJobPosting = savedJobPosting != null ? savedJobPosting : new HashSet<>();
    }
}
