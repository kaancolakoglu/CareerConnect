package com.springframework.CareerConnect.model;

import lombok.*;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateCompanyDetailsDTO {

    private String companyName;
    private String companyRegistrationNumber;
    private String sectorName;
    private Long companySize;
    private String companyDescription;
    private String companyWebsite;
    private String companyLogoUrl;
    private Set<String> addresses;

}
