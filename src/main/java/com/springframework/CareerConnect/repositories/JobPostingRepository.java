package com.springframework.CareerConnect.repositories;

import com.springframework.CareerConnect.domain.JobPosting;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobPostingRepository extends CrudRepository<JobPosting, String> {
}
