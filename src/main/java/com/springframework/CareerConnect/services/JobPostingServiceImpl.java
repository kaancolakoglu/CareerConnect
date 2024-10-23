package com.springframework.CareerConnect.services;

import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import com.springframework.CareerConnect.domain.JobPosting;
import com.springframework.CareerConnect.repositories.JobPostingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class JobPostingServiceImpl implements JobPostingService {

    private final JobPostingRepository jobPostingRepository;

    public JobPostingServiceImpl(JobPostingRepository jobPostingRepository) {
        this.jobPostingRepository = jobPostingRepository;
    }
    @Override
    public Iterable<JobPosting> findAllJobPostings() {
        log.debug("Retrieving all job postings");
        return jobPostingRepository.findAll();
    }
    public JobPosting findJobPostingById(Long id) {
        return jobPostingRepository.findById(String.valueOf(id))
                .orElseThrow(() -> new ResourceNotFoundException("JobPosting not found"));
    }

    @Override
    public JobPosting createJobPosting(JobPosting jobPosting) {
        return jobPostingRepository.save(jobPosting);
    }

}
