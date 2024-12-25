package com.springframework.CareerConnect.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SkillDTO {
    private Long skillId;
    private String name;
}
