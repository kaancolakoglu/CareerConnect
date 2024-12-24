package com.springframework.CareerConnect.services;

import com.springframework.CareerConnect.Mapper.MapStructMapper;
import com.springframework.CareerConnect.domain.Application;
import com.springframework.CareerConnect.domain.JobPosting;
import com.springframework.CareerConnect.domain.User;
import com.springframework.CareerConnect.enums.ApplicationStatus;
import com.springframework.CareerConnect.exceptions.ApplicationNotFoundException;
import com.springframework.CareerConnect.exceptions.JobPostingNotFoundException;
import com.springframework.CareerConnect.exceptions.UserNotFoundException;
import com.springframework.CareerConnect.model.ApplicationDTO;
import com.springframework.CareerConnect.repositories.ApplicationRepository;
import com.springframework.CareerConnect.repositories.JobPostingRepository;
import com.springframework.CareerConnect.repositories.UserRepository;
import com.sun.jdi.request.DuplicateRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final JobPostingRepository jobPostingRepository;
    private final UserRepository userRepository;
    private final MapStructMapper mapStructMapper;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository,
                                  JobPostingRepository jobPostingRepository,
                                  UserRepository userRepository,
                                  MapStructMapper mapStructMapper) {
        this.applicationRepository = applicationRepository;
        this.jobPostingRepository = jobPostingRepository;
        this.userRepository = userRepository;
        this.mapStructMapper = mapStructMapper;
    }

    @Override
    public ApplicationDTO submitApplication(ApplicationDTO applicationDTO) {

        JobPosting jobPosting = jobPostingRepository.findJobPostingByJobId(applicationDTO.getJobId())
                .orElseThrow(() -> new JobPostingNotFoundException("Job posting not found with ID: " + applicationDTO.getJobId()));

        User user = userRepository.findById(applicationDTO.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + applicationDTO.getUserId()));

        boolean applicationExists = applicationRepository.existsByUserProfileIdAndJobPostingJobId(
                applicationDTO.getUserId(),
                applicationDTO.getJobId()
        );

        if (applicationExists) {
            throw new DuplicateRequestException("Application already exists");
        }

        Application application = mapStructMapper.mapToApplication(applicationDTO);
        application.setJobPosting(jobPosting);
        application.setUser(user);
        application.setSubmissionDate(LocalDateTime.now());
        application.setApplicationStatus(ApplicationStatus.PENDING);

        Application savedApplication = applicationRepository.save(application);

        return mapStructMapper.mapToApplicationDTO(savedApplication);
    }

    @Override
    public List<ApplicationDTO> findApplicationsByUser(Long userId) {
        List<Application> applications = applicationRepository.findByUser_ProfileId(userId);
        return applications.stream()
                .map(mapStructMapper::mapToApplicationDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ApplicationDTO> findApplicationsByJob(Long jobId) {
        List<Application> applications = applicationRepository.findByJobPostingJobId(jobId);
        return applications.stream()
                .map(mapStructMapper::mapToApplicationDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ApplicationDTO updateApplicationStatus(Long applicationId, ApplicationStatus applicationStatus) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new ApplicationNotFoundException("Application not found with ID: " + applicationId));

        application.setApplicationStatus(applicationStatus);
        Application updatedApplication = applicationRepository.save(application);
        return mapStructMapper.mapToApplicationDTO(updatedApplication);
    }

}
