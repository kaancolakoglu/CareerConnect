package com.springframework.CareerConnect.controllers;

import com.springframework.CareerConnect.enums.ApplicationStatus;
import com.springframework.CareerConnect.model.ApplicationDTO;
import com.springframework.CareerConnect.services.ApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping
    public ResponseEntity<ApplicationDTO> submitApplication(@RequestBody ApplicationDTO applicationDTO) throws Exception {
        ApplicationDTO savedApplication = applicationService.submitApplication(applicationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedApplication);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ApplicationDTO>> getUserApplications(@PathVariable Long userId) {
        List<ApplicationDTO> userApplications = applicationService.findApplicationsByUser(userId);
        return ResponseEntity.ok(userApplications);
    }

    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<ApplicationDTO>> getJobApplications(@PathVariable Long jobId) {
        List<ApplicationDTO> jobApplications = applicationService.findApplicationsByJob(jobId);
        return ResponseEntity.ok(jobApplications);
    }

    @PutMapping("/{applicationId}/status")
    public ResponseEntity<ApplicationDTO> updateApplicationStatus(
            @PathVariable Long applicationId,
            @RequestParam("status") ApplicationStatus status) throws Exception {
        ApplicationDTO updatedApplication = applicationService.updateApplicationStatus(applicationId, status);
        return ResponseEntity.ok(updatedApplication);
    }
}
