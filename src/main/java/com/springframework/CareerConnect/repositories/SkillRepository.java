package com.springframework.CareerConnect.repositories;

import com.springframework.CareerConnect.domain.Skill;
import org.springframework.data.repository.CrudRepository;

public interface SkillRepository extends CrudRepository<Skill, Long> {
}
