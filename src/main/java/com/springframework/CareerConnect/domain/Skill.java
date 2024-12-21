package com.springframework.CareerConnect.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "skill")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long skillId;

    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "skills")
    private Set<Resume> resume = new HashSet<>();

}
