package com.springframework.CareerConnect.services;


import com.springframework.CareerConnect.Mapper.MapStructMapper;
import com.springframework.CareerConnect.domain.Address;
import com.springframework.CareerConnect.domain.Company;
import com.springframework.CareerConnect.model.CompanyProfileDetailsDTO;
import com.springframework.CareerConnect.model.UpdateCompanyDetailsDTO;
import com.springframework.CareerConnect.repositories.AddressRepository;
import com.springframework.CareerConnect.repositories.CompanyRepository;
import com.springframework.CareerConnect.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final MapStructMapper mapStructMapper;
    private final RoleRepository roleRepository;
    private final AddressRepository addressRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository, MapStructMapper mapStructMapper, RoleRepository roleRepository, AddressRepository addressRepository) {
        this.companyRepository = companyRepository;
        this.mapStructMapper = mapStructMapper;
        this.roleRepository = roleRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public CompanyProfileDetailsDTO getCompanyDetails(Long companyId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Company not found with id: " + companyId));

        return mapStructMapper.mapToCompanyProfileDetailsDTO(company);
    }

    @Override
    public CompanyProfileDetailsDTO updateCompanyDetails(Long companyId, UpdateCompanyDetailsDTO updateDTO) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Company not found with ID: " + companyId));

        mapStructMapper.mapToCompany(updateDTO);

        if (updateDTO.getCompanyName() != null) {
            company.setCompanyName(updateDTO.getCompanyName());
        }
        if (updateDTO.getCompanyRegistrationNumber() != null) {
            company.setCompanyRegistrationNumber(updateDTO.getCompanyRegistrationNumber());
        }
        if (updateDTO.getSectorName() != null) {
            company.setSectorName(updateDTO.getSectorName());
        }
        if (updateDTO.getCompanySize() != null) {
            company.setCompanySize(updateDTO.getCompanySize());
        }
        if (updateDTO.getCompanyDescription() != null) {
            company.setCompanyDescription(updateDTO.getCompanyDescription());
        }
        if (updateDTO.getCompanyWebsite() != null) {
            company.setCompanyWebsite(updateDTO.getCompanyWebsite());
        }
        if (updateDTO.getCompanyLogoUrl() != null) {
            company.setCompanyLogoUrl(updateDTO.getCompanyLogoUrl());
        }

        if (updateDTO.getAddresses() != null && !updateDTO.getAddresses().isEmpty()) {
            Set<Address> updatedAddresses = updateDTO.getAddresses().stream()
                    .map(addressString -> {
                        String[] parts = addressString.split(", ");
                        if (parts.length != 5) {
                            throw new IllegalArgumentException("Invalid address format: " + addressString);
                        }
                        return Address.builder()
                                .street(parts[0])
                                .city(parts[1])
                                .state(parts[2])
                                .country(parts[3])
                                .zipCode(parts[4])
                                .build();
                    })
                    .collect(Collectors.toSet());

            addressRepository.saveAll(updatedAddresses);

            company.setAddress(updatedAddresses);
        }
        Company updatedCompany = companyRepository.save(company);

        return mapStructMapper.mapToCompanyProfileDetailsDTO(updatedCompany);
    }

    @Override
    public List<CompanyProfileDetailsDTO> getAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        return companies.stream()
                .map(mapStructMapper::mapToCompanyProfileDetailsDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCompany(Long companyId) {
        if (!companyRepository.existsById(companyId)) {
            throw new RuntimeException("Company not found with ID: " + companyId);
        }
        companyRepository.deleteById(companyId);
    }

    @Override
    public List<CompanyProfileDetailsDTO> searchCompanies(String name, String sector, Long minSize, Long maxSize) {
        List<Company> companies = companyRepository.findCompaniesByCriteria(name, sector, minSize, maxSize);
        return companies.stream()
                .map(mapStructMapper::mapToCompanyProfileDetailsDTO)
                .collect(Collectors.toList());
    }
}
