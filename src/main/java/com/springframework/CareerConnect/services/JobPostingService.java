package com.springframework.CareerConnect.services;

import com.springframework.CareerConnect.domain.JobPosting;
import com.springframework.CareerConnect.model.JobPostingRequest;
import org.springframework.stereotype.Service;

@Service
public interface JobPostingService {
    Iterable<JobPosting> findAllJobPostings();

    JobPosting findJobPostingById(Long companyId);

    JobPosting createJobPosting(Long companyId,JobPostingRequest jobPostingRequest);
}
