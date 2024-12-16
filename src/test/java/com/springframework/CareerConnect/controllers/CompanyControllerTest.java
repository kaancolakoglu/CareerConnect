package com.springframework.CareerConnect.controllers;

import com.springframework.CareerConnect.model.CompanyProfileDetailsDTO;
import com.springframework.CareerConnect.model.UpdateCompanyDetailsDTO;
import com.springframework.CareerConnect.services.CompanyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CompanyControllerTest {

    @InjectMocks
    private CompanyController companyController;

    @Mock
    private CompanyService companyService;

    private CompanyProfileDetailsDTO mockCompanyDTO;

    @BeforeEach
    void setUp() {
        mockCompanyDTO = new CompanyProfileDetailsDTO();
        mockCompanyDTO.setCompanyName("Test Company");
        mockCompanyDTO.setCompanyRegistrationNumber("REG123");
        mockCompanyDTO.setSectorName("Technology");
        mockCompanyDTO.setCompanySize(500L);
        mockCompanyDTO.setCompanyDescription("A leading tech company.");
        mockCompanyDTO.setCompanyWebsite("http://testcompany.com");
        mockCompanyDTO.setCompanyLogoUrl("http://testcompany.com/logo.png");
        mockCompanyDTO.setAddresses(Set.of("123 Main St, City, State, Country, 12345"));
    }

    @Test
    void testGetCompanyDetails() {
        // Arrange
        when(companyService.getCompanyDetails(1L)).thenReturn(mockCompanyDTO);

        // Act
        ResponseEntity<CompanyProfileDetailsDTO> response = companyController.getCompanyDetails(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockCompanyDTO, response.getBody());
        verify(companyService, times(1)).getCompanyDetails(1L);
    }

    @Test
    void testUpdateCompanyDetails() {
        // Arrange
        UpdateCompanyDetailsDTO updateDTO = new UpdateCompanyDetailsDTO();
        updateDTO.setCompanyName("Updated Company");
        updateDTO.setCompanyDescription("An updated description.");
        when(companyService.updateCompanyDetails(1L, updateDTO)).thenReturn(mockCompanyDTO);

        // Act
        ResponseEntity<CompanyProfileDetailsDTO> response = companyController.updateCompanyDetails(1L, updateDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockCompanyDTO, response.getBody());
        verify(companyService, times(1)).updateCompanyDetails(1L, updateDTO);
    }

    @Test
    void testGetAllCompanies() {
        // Arrange
        List<CompanyProfileDetailsDTO> companies = List.of(mockCompanyDTO);
        when(companyService.getAllCompanies()).thenReturn(companies);

        // Act
        ResponseEntity<List<CompanyProfileDetailsDTO>> response = companyController.getAllCompanies();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(companies, response.getBody());
        verify(companyService, times(1)).getAllCompanies();
    }

    @Test
    void testDeleteCompany() {
        // Arrange
        doNothing().when(companyService).deleteCompany(1L);

        // Act
        ResponseEntity<Void> response = companyController.deleteCompany(1L);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(companyService, times(1)).deleteCompany(1L);
    }

    @Test
    void testSearchCompanies() {
        // Arrange
        List<CompanyProfileDetailsDTO> companies = List.of(mockCompanyDTO);
        when(companyService.searchCompanies("Test Company", "Technology", 100L, 1000L)).thenReturn(companies);

        // Act
        ResponseEntity<List<CompanyProfileDetailsDTO>> response = companyController.searchCompanies(
                "Test Company", "Technology", 100L, 1000L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(companies, response.getBody());
        verify(companyService, times(1)).searchCompanies("Test Company", "Technology", 100L, 1000L);
    }
}