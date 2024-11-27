package com.springframework.CareerConnect.model;

import com.springframework.CareerConnect.domain.JobPosting;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TagDTO {
    private Long tagId;
    private String tagName;
}
