package com.springframework.CareerConnect.model;

import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ResumeDTO {
    private String userName;
    private String userLastName;

    private Long resumeId;
    private String resumeName;
    private Set<EducationDTO> educations;
    private Set<ExperienceDTO> experiences;
    private Set<SkillDTO> skills;

}
