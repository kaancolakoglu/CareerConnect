package com.springframework.CareerConnect.controllers;

import com.springframework.CareerConnect.model.CompanyProfileDetailsDTO;
import com.springframework.CareerConnect.model.UpdateCompanyDetailsDTO;
import com.springframework.CareerConnect.services.CompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/company")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<CompanyProfileDetailsDTO> getCompanyDetails(@PathVariable Long companyId) {
        CompanyProfileDetailsDTO companyDetails = companyService.getCompanyDetails(companyId);
        return ResponseEntity.ok(companyDetails);
    }


    @PutMapping("/{companyId}")
    public ResponseEntity<CompanyProfileDetailsDTO> updateCompanyDetails(
            @PathVariable Long companyId,
            @RequestBody UpdateCompanyDetailsDTO updateDTO) {
        CompanyProfileDetailsDTO updatedCompany = companyService.updateCompanyDetails(companyId, updateDTO);
        return ResponseEntity.ok(updatedCompany);
    }

    @GetMapping
    public ResponseEntity<List<CompanyProfileDetailsDTO>> getAllCompanies() {
        List<CompanyProfileDetailsDTO> companies = companyService.getAllCompanies();
        return ResponseEntity.ok(companies);
    }

    @DeleteMapping("/{companyId}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long companyId) {
        companyService.deleteCompany(companyId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<CompanyProfileDetailsDTO>> searchCompanies(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String sector,
            @RequestParam(required = false) Long minSize,
            @RequestParam(required = false) Long maxSize) {
        List<CompanyProfileDetailsDTO> companies = companyService.searchCompanies(name, sector, minSize, maxSize);
        return ResponseEntity.ok(companies);
    }
}
