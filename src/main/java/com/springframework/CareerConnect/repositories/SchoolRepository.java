package com.springframework.CareerConnect.repositories;

import com.springframework.CareerConnect.domain.School;
import org.springframework.data.repository.CrudRepository;

public interface SchoolRepository extends CrudRepository<School, Long> {
}
