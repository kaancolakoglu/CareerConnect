package com.springframework.CareerConnect.model;

import com.springframework.CareerConnect.domain.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CompanyDTO {

    private Long companyId;
    private String name;
    private List<Address> addresses;
    private List<JobPostingDTO> jobPostings;
}
