package com.springframework.CareerConnect.repositories;

import com.springframework.CareerConnect.domain.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    Optional<Application> findByApplicationId(Long jobId);

    List<Application> findByUser_ProfileId(Long userId);

    List<Application> findByJobPostingJobId(Long jobId);

    boolean existsByUserProfileIdAndJobPostingJobId(Long userId, Long jobId);
}
