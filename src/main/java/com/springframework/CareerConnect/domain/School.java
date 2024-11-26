package com.springframework.CareerConnect.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long schoolId;

    private String schoolName;

    @ManyToMany(mappedBy = "school")
    private Set<User> user;

    @ManyToMany
    @JoinTable(name = "school_education",
            joinColumns = @JoinColumn(name = "school_id"),
            inverseJoinColumns = @JoinColumn(name = "education_id")
    )
    private Set<Education> education;

    @OneToMany(mappedBy = "school")
    private Set<Address> address;
}
