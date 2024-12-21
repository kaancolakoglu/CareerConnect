package com.springframework.CareerConnect.services;

import com.springframework.CareerConnect.domain.Education;
import com.springframework.CareerConnect.domain.Experience;
import com.springframework.CareerConnect.domain.Resume;
import com.springframework.CareerConnect.domain.Skill;
import org.springframework.stereotype.Service;

@Service
public interface ResumeService {
    Resume createResume(Long userId, Resume resume);
    Resume addExperience(Long resumeId, Experience experience);
    Resume addEducation(Long resumeId, Education education);

    Resume addSkill(Long resumeId, Skill skill);

}
