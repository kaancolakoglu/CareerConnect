package com.springframework.CareerConnect.services;

import com.springframework.CareerConnect.enums.ApplicationStatus;
import com.springframework.CareerConnect.model.ApplicationDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ApplicationService {

    ApplicationDTO submitApplication(ApplicationDTO applicationDTO) throws Exception;

    List<ApplicationDTO> findApplicationsByUser(Long userId);

    List<ApplicationDTO> findApplicationsByJob(Long jobId);

    ApplicationDTO updateApplicationStatus(Long applicationId, ApplicationStatus applicationStatus) throws Exception;
}
