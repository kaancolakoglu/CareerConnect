package com.springframework.CareerConnect.model;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyProfileDetailsDTO {
    private String companyName;
    private String companyRegistrationNumber;
    private String sectorName;
    private Long companySize;
    private String companyDescription;
    private String companyWebsite;
    private String companyLogoUrl;
    private Set<String> addresses; // Converted to a list of strings for simplicity
}