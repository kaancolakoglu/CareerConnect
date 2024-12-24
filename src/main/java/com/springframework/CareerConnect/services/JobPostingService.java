package com.springframework.CareerConnect.services;

import com.springframework.CareerConnect.domain.JobPosting;
import com.springframework.CareerConnect.model.JobPostingRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JobPostingService {
    Iterable<JobPosting> findAllJobPostings();

    JobPosting findJobPostingById(Long companyId);

    JobPosting createJobPosting(Long companyId,JobPostingRequest jobPostingRequest);

    List<JobPosting> findJobPostingByCriteria(String jobTitle, String jobLocation, String companyName, String tagName, String sectorName, Long companyId);
}
