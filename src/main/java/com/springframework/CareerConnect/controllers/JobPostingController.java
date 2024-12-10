package com.springframework.CareerConnect.controllers;

import com.springframework.CareerConnect.Mapper.MapStructMapper;
import com.springframework.CareerConnect.domain.JobPosting;
import com.springframework.CareerConnect.model.JobPostingDTO;
import com.springframework.CareerConnect.services.JobPostingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/jobPosting")
public class JobPostingController {
    private final JobPostingService jobPostingService;
    private final MapStructMapper mapStructMapper;

    public JobPostingController(JobPostingService jobPostingService, MapStructMapper mapStructMapper) {
        this.jobPostingService = jobPostingService;
        this.mapStructMapper = mapStructMapper;
    }

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('COMPANY') or hasRole('ADMIN')")
    public ResponseEntity<List<JobPostingDTO>> listJobPostings() {
        List<JobPosting> jobPostings = (List<JobPosting>) jobPostingService.findAllJobPostings();

        List<JobPostingDTO> jobPostingDTOS = jobPostings.stream()
                .map(mapStructMapper::mapToJobPostingDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(jobPostingDTOS);
    }

    @GetMapping("/{jobId}")
    @PreAuthorize("hasRole('USER') or hasRole('COMPANY') or hasRole('ADMIN')")
    public ResponseEntity<JobPosting> getJobPostingById(@PathVariable Long jobId) {
        JobPosting jobPosting = jobPostingService.findJobPostingById(jobId);
        return ResponseEntity.ok(jobPosting);
    }

    @PostMapping
    @PreAuthorize("hasRole('COMPANY') or hasRole('ADMIN')")
    public ResponseEntity<JobPosting> createJobPosting(@RequestBody JobPosting jobPosting) {
        JobPosting savedJobPosting = jobPostingService.createJobPosting(jobPosting);
        return new ResponseEntity<>(savedJobPosting, HttpStatus.CREATED);
    }
}
