package com.springframework.CareerConnect.repositories;

import com.springframework.CareerConnect.domain.Tag;
import org.springframework.data.repository.CrudRepository;

public interface TagRepository extends CrudRepository<Tag, Long> {
}
