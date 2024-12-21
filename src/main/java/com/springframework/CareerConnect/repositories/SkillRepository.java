package com.springframework.CareerConnect.repositories;

import com.springframework.CareerConnect.domain.Skill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends CrudRepository<Skill, Long> {
    List<Skill> findByNameIgnoreCase(String name);
}
