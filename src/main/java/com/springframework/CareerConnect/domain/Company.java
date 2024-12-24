package com.springframework.CareerConnect.domain;

import com.springframework.CareerConnect.enums.ERole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "company")
public class Company extends BaseUser {

    @NotBlank
    private String companyName;

    @NotBlank
    private String companyRegistrationNumber;

    @NotBlank
    private String sectorName;

    @NotBlank
    private Long companySize;

    private String companyDescription;
    private String companyWebsite;

    private String companyLogoUrl;

    @ManyToMany
    @JoinTable(name = "company_address",
            joinColumns = @JoinColumn(name = "company_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id"),
            foreignKey = @ForeignKey(name = "FK_COMPANY_ADDRESS"),
            inverseForeignKey = @ForeignKey(name = "FK_ADDRESS_COMPANY")
    )
    private Set<Address> addressId = new HashSet<>();

    @OneToMany(mappedBy = "company")
    private Set<User> user;

    @OneToMany(mappedBy = "company")
    private Set<JobPosting> jobPosting;

    @Builder
    public Company(String companyName, String companyRegistrationNumber,
                   String sectorName, Long companySize, String companyDescription, String companyWebsite,
                   Long profileId, String name, String email, String phoneNumber, String password,
                   LocalDateTime createdDate, LocalDateTime updatedDate, String companyLogoUrl,
                   LocalDateTime lastLoginDate, String status, ERole role,
                   Set<Address> addressId, Set<User> user, Set<JobPosting> jobPosting) {
        super(profileId, name, email,phoneNumber, password, createdDate, updatedDate, lastLoginDate, status, role);
        this.companyName = companyName;
        this.companyRegistrationNumber = companyRegistrationNumber;
        this.sectorName = sectorName;
        this.companySize = companySize;
        this.companyDescription = companyDescription;
        this.companyWebsite = companyWebsite;
        this.addressId = addressId;
        this.user = user;
        this.jobPosting = jobPosting;
        this.companyLogoUrl = companyLogoUrl;

    }

    @Builder
    public Company(String companyName, String companyRegistrationNumber,String sectorName, Long companySize, String username, String email,String password) {
        super(username, email, password);
        this.companyName = companyName;
        this.companyRegistrationNumber = companyRegistrationNumber;
        this.companySize = companySize;
        this.addressId = new HashSet<>();
        this.user = new HashSet<>();
        this.jobPosting = new HashSet<>();
        this.sectorName = sectorName;
    }

    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "profile_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            foreignKey = @ForeignKey(name = "FK_USER_ROLE"),
            inverseForeignKey = @ForeignKey(name = "FK_ROLE_USER")
    )
    private Set<Role> roles = new HashSet<>();
}
