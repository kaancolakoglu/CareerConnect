package com.springframework.CareerConnect.services;

import com.springframework.CareerConnect.Mapper.MapStructMapper;
import com.springframework.CareerConnect.Mapper.MapStructMapperImpl;
import com.springframework.CareerConnect.domain.Company;
import com.springframework.CareerConnect.domain.JobPosting;
import com.springframework.CareerConnect.domain.Tag;
import com.springframework.CareerConnect.exceptions.ResourceNotFoundException;
import com.springframework.CareerConnect.model.JobPostingRequest;
import com.springframework.CareerConnect.repositories.CompanyRepository;
import com.springframework.CareerConnect.repositories.JobPostingRepository;
import com.springframework.CareerConnect.repositories.TagRepository;
import com.springframework.CareerConnect.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@Slf4j
public class JobPostingServiceImpl implements JobPostingService {

    private final JobPostingRepository jobPostingRepository;
    private final TagRepository tagRepository;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final MapStructMapper mapStructMapper;

    public JobPostingServiceImpl(JobPostingRepository jobPostingRepository, TagRepository tagRepository, UserRepository userRepository, CompanyRepository companyRepository, MapStructMapperImpl mapStructMapper) {
        this.jobPostingRepository = jobPostingRepository;
        this.tagRepository = tagRepository;
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
        this.mapStructMapper = mapStructMapper;
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
    public JobPosting createJobPosting(Long companyId,JobPostingRequest jobPostingRequest) {

        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("Company with id " + companyId + " not found"));

        Set<Tag> tag = jobPostingRequest.getTag().stream()
                .map(tagName -> tagRepository.findByTagName(tagName)
                        .orElseGet(() -> new Tag(tagName)))
                .collect(Collectors.toSet());
        tagRepository.saveAll(tag);

        JobPosting newJobPosting = new JobPosting();
        newJobPosting.setJobTitle(jobPostingRequest.getJobTitle());
        newJobPosting.setJobDescription(jobPostingRequest.getJobDescription());
        newJobPosting.setJobLocation(jobPostingRequest.getJobLocation());
        newJobPosting.setCompany(company);
        newJobPosting.setDeadline(jobPostingRequest.getDeadline());
        newJobPosting.setTag(tag);
        JobPosting savedJobPosting = jobPostingRepository.save(newJobPosting);

        company.setJobPosting(Collections.singleton(savedJobPosting));


        return savedJobPosting;
    }
}
