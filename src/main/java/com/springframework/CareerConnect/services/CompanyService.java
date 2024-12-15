package com.springframework.CareerConnect.services;

import com.springframework.CareerConnect.model.CompanyProfileDetailsDTO;
import com.springframework.CareerConnect.model.UpdateCompanyDetailsDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompanyService {
    CompanyProfileDetailsDTO getCompanyDetails(Long companyId);

    CompanyProfileDetailsDTO updateCompanyDetails(Long companyId, UpdateCompanyDetailsDTO updateDTO);

    List<CompanyProfileDetailsDTO> getAllCompanies();

    void deleteCompany(Long companyId);

    List<CompanyProfileDetailsDTO> searchCompanies(String name, String sector, Long minSize, Long maxSize);
}
