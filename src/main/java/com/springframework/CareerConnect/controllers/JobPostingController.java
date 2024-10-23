package com.springframework.CareerConnect.controllers;

import com.springframework.CareerConnect.domain.JobPosting;
import com.springframework.CareerConnect.services.JobPostingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
@Slf4j
public class JobPostingController {
    private final JobPostingService jobPostingService;

    public JobPostingController(JobPostingService jobPostingService) {
        this.jobPostingService = jobPostingService;
    }

    @GetMapping("/jobPosting")
    public Iterable<JobPosting> listJobPostings(){
        return jobPostingService.findAllJobPostings();
    }

    @GetMapping("jobPosting/{jobId}")
    public ResponseEntity<JobPosting> getJobPostingById(@PathVariable Long jobId) {
        JobPosting jobPosting = jobPostingService.findJobPostingById(jobId);
        return ResponseEntity.ok(jobPosting);
    }

    @PostMapping("/jobPosting")
    public ResponseEntity<?> createJobPosting(@RequestBody JobPosting jobPosting) {
        JobPosting savedJobPosting = jobPostingService.createJobPosting(jobPosting);
        return new ResponseEntity<>(savedJobPosting, HttpStatus.CREATED);
    }




}
