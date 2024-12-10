package com.springframework.CareerConnect.services;

import com.springframework.CareerConnect.domain.JobPosting;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public interface JobPostingService {
    Iterable<JobPosting> findAllJobPostings();

    JobPosting findJobPostingById(Long jobId);

    @PreAuthorize("hasRole('COMPANY') or hasRole('ADMIN')")
    JobPosting createJobPosting(JobPosting jobPosting);
}
