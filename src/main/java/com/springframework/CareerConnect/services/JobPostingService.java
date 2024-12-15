package com.springframework.CareerConnect.services;

import com.springframework.CareerConnect.domain.JobPosting;
import org.springframework.stereotype.Service;

@Service
public interface JobPostingService {
    Iterable<JobPosting> findAllJobPostings();

    JobPosting findJobPostingById(Long jobId);

    JobPosting createJobPosting(JobPosting jobPosting);
}
