package com.springframework.CareerConnect.repositories;

import com.springframework.CareerConnect.domain.Company;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<Company, Long> {
}
