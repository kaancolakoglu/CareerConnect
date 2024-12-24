package com.springframework.CareerConnect.controllers;

import com.springframework.CareerConnect.Mapper.JobPostingMapper;
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

    @GetMapping("/jobPosting")
    //@PreAuthorize("hasRole('admin')")
    public ResponseEntity<List<JobPostingDTO>> listJobPostings() {
        List<JobPosting> jobPostings = (List<JobPosting>) jobPostingService.findAllJobPostings();

        List<JobPostingDTO> jobPostingDTOS = jobPostings.stream()
                .map(JobPostingMapper::toJobPostingDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(jobPostingDTOS);
    }

    @GetMapping("/jobPosting/{jobId}")
    //@PreAuthorize("hasRole('admin')")
    public ResponseEntity<JobPostingDTO> getJobPostingById(@PathVariable Long jobId) {
        JobPostingDTO jobPosting = JobPostingMapper.toJobPostingDTO(jobPostingService.findJobPostingById(jobId));

        return ResponseEntity.ok(jobPosting);
    }

    @PostMapping("/jobPosting")
    //@PreAuthorize("hasRole('admin')")
    public ResponseEntity<JobPostingDTO> createJobPosting(@RequestBody JobPostingRequest jobPostingRequest) {
        JobPosting savedJobPosting = jobPostingService.createJobPosting(
                jobPostingRequest.getCompanyId(),
                jobPostingRequest
        );
        JobPostingDTO jobPostingDTO= JobPostingMapper.toJobPostingDTO(savedJobPosting);
        return new ResponseEntity<>(jobPostingDTO, HttpStatus.CREATED);
    }

    @GetMapping("/jobPosting/search")
    public ResponseEntity<List<JobPostingDTO>> searchJobPostings(
            @RequestParam(required = false) String jobTitle,
            @RequestParam(required = false) String jobLocation,
            @RequestParam(required = false) String companyName,
            @RequestParam(required = false) String tagName,
            @RequestParam(required = false) String sectorName,
            @RequestParam(required = false) Long companyId
    ) {

        List<JobPosting> filteredJobPosting = jobPostingService.findJobPostingByCriteria(jobTitle, jobLocation, companyName, tagName, sectorName, companyId);

        List<JobPostingDTO> jobPostingDTOs = filteredJobPosting.stream()
                .map(JobPostingMapper::toJobPostingDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(jobPostingDTOs);
    }
}
