package com.springframework.CareerConnect.domain;


import com.springframework.CareerConnect.enums.ERole;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public abstract class BaseUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileId;

    @NotBlank
    @Size(min = 1, max = 20)
    private String username;

    @NotBlank
    @Email
    @Size(min = 5, max = 50)
    private String email;

    @NotBlank
    @Size(min =1, max = 120)
    private String password;

    @NotBlank
    private LocalDateTime createdDate;

    @NotBlank
    private LocalDateTime updatedDate;

    @NotBlank
    private String lastLoginDate;

    @NotBlank
    private String status;

    @NotBlank
    private ERole role;

    protected BaseUser(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
