package com.springframework.CareerConnect.controllers;

import com.springframework.CareerConnect.enums.ApplicationStatus;
import com.springframework.CareerConnect.model.ApplicationDTO;
import com.springframework.CareerConnect.services.ApplicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApplicationControllerTest {

    @InjectMocks
    private ApplicationController applicationController;

    @Mock
    private ApplicationService applicationService;

    private ApplicationDTO mockApplicationDTO;

    @BeforeEach
    void setUp() {
        mockApplicationDTO = new ApplicationDTO();
        mockApplicationDTO.setId(1L);
        mockApplicationDTO.setJobId(1L);
        mockApplicationDTO.setUserId(1L);
        mockApplicationDTO.setStatus("PENDING");
        mockApplicationDTO.setSubmissionDate(LocalDateTime.now());
    }

    @Test
    void testSubmitApplication() throws Exception {
        // Arrange
        when(applicationService.submitApplication(any(ApplicationDTO.class)))
                .thenReturn(mockApplicationDTO);

        // Act
        ResponseEntity<ApplicationDTO> response = applicationController.submitApplication(mockApplicationDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockApplicationDTO, response.getBody());
        verify(applicationService, times(1)).submitApplication(any(ApplicationDTO.class));
    }

    @Test
    void testGetUserApplications() {
        // Arrange
        List<ApplicationDTO> mockApplications = List.of(mockApplicationDTO);
        when(applicationService.findApplicationsByUser(1L)).thenReturn(mockApplications);

        // Act
        ResponseEntity<List<ApplicationDTO>> response = applicationController.getUserApplications(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockApplications, response.getBody());
        verify(applicationService, times(1)).findApplicationsByUser(1L);
    }

    @Test
    void testGetJobApplications() {
        // Arrange
        List<ApplicationDTO> mockApplications = List.of(mockApplicationDTO);
        when(applicationService.findApplicationsByJob(1L)).thenReturn(mockApplications);

        // Act
        ResponseEntity<List<ApplicationDTO>> response = applicationController.getJobApplications(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockApplications, response.getBody());
        verify(applicationService, times(1)).findApplicationsByJob(1L);
    }

    @Test
    void testUpdateApplicationStatus() throws Exception {
        // Arrange
        mockApplicationDTO.setStatus("ACCEPTED");
        when(applicationService.updateApplicationStatus(eq(1L), eq(ApplicationStatus.ACCEPTED)))
                .thenReturn(mockApplicationDTO);

        // Act
        ResponseEntity<ApplicationDTO> response = applicationController.updateApplicationStatus(1L, ApplicationStatus.ACCEPTED);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockApplicationDTO, response.getBody());
        verify(applicationService, times(1)).updateApplicationStatus(1L, ApplicationStatus.ACCEPTED);
    }
}