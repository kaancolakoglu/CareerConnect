package com.springframework.CareerConnect.repositories;

import com.springframework.CareerConnect.domain.Education;
import org.springframework.data.repository.CrudRepository;

public interface EducationRepository extends CrudRepository<Education, Long> {
}
