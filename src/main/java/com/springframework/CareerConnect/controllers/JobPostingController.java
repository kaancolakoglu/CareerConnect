package com.springframework.CareerConnect.controllers;

import com.springframework.CareerConnect.Mapper.MapStructMapper;
import com.springframework.CareerConnect.domain.JobPosting;
import com.springframework.CareerConnect.model.JobPostingDTO;
import com.springframework.CareerConnect.model.JobPostingRequest;
import com.springframework.CareerConnect.services.JobPostingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1")
@Slf4j
public class JobPostingController {
    private final JobPostingService jobPostingService;
    private final MapStructMapper mapStructMapper;

    public JobPostingController(JobPostingService jobPostingService, MapStructMapper mapStructMapper) {
        this.jobPostingService = jobPostingService;
        this.mapStructMapper = mapStructMapper;
    }

    //TODO: FIX
    @GetMapping("/jobPosting")
    //@PreAuthorize("hasRole('admin')")
    public ResponseEntity<List<JobPostingDTO>> listJobPostings() {
        List<JobPosting> jobPostings = (List<JobPosting>) jobPostingService.findAllJobPostings();

        List<JobPostingDTO> jobPostingDTOS = jobPostings.stream()
                .map(mapStructMapper::mapToJobPostingDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(jobPostingDTOS);
    }

    @GetMapping("/jobPosting/{jobId}")
    //@PreAuthorize("hasRole('admin')")
    public ResponseEntity<JobPostingDTO> getJobPostingById(@PathVariable Long jobId) {
        JobPostingDTO jobPosting = mapStructMapper.mapToJobPostingDTO(jobPostingService.findJobPostingById(jobId));

        return ResponseEntity.ok(jobPosting);
    }

    @PostMapping("/jobPosting")
    //@PreAuthorize("hasRole('admin')")
    public ResponseEntity<JobPostingDTO> createJobPosting(@RequestBody JobPostingRequest jobPostingRequest) {
        JobPosting savedJobPosting = jobPostingService.createJobPosting(
                jobPostingRequest.getCompanyId(),
                jobPostingRequest
        );
        JobPostingDTO jobPostingDTO= mapStructMapper.mapToJobPostingDTO(savedJobPosting);
        return new ResponseEntity<>(jobPostingDTO, HttpStatus.CREATED);
    }
}
