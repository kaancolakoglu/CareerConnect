package com.springframework.CareerConnect.services;

import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import com.springframework.CareerConnect.domain.JobPosting;
import com.springframework.CareerConnect.repositories.CompanyRepository;
import com.springframework.CareerConnect.repositories.JobPostingRepository;
import com.springframework.CareerConnect.repositories.TagRepository;
import com.springframework.CareerConnect.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;


@Service
@Slf4j
public class JobPostingServiceImpl implements JobPostingService {

    private final JobPostingRepository jobPostingRepository;
    private final TagRepository tagRepository;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    public JobPostingServiceImpl(JobPostingRepository jobPostingRepository, TagRepository tagRepository, UserRepository userRepository, CompanyRepository companyRepository) {
        this.jobPostingRepository = jobPostingRepository;
        this.tagRepository = tagRepository;
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
    }
    @Override
    public Iterable<JobPosting> findAllJobPostings() {
        log.debug("Retrieving all job postings");
        return jobPostingRepository.findAll();

    }
    public JobPosting findJobPostingById(Long id) {
        return jobPostingRepository.findById(String.valueOf(id))
                .orElseThrow(() -> new NoSuchElementException("JobPosting not found"));
    }

    @Override
    public JobPosting createJobPosting(JobPosting jobPosting) {
        return jobPostingRepository.save(jobPosting);
    }

}
