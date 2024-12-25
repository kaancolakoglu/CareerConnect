package com.springframework.CareerConnect.services;

import com.springframework.CareerConnect.domain.Education;
import com.springframework.CareerConnect.domain.Experience;
import com.springframework.CareerConnect.domain.Resume;
import com.springframework.CareerConnect.domain.Skill;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ResumeService {
    Resume createResume(Long userId, Resume resume);
    Resume addExperience(Long resumeId, Experience experience);
    Resume addEducation(Long resumeId, Education education);
    Resume addSkill(Long resumeId, Skill skill);

    void deleteResumeById(Long resumeId);
    void deleteExperienceById(Long resumeId, Long experienceId);
    void deleteEducationById(Long resumeId, Long educationId);
    void deleteSkillById(Long resumeId, Long skillId);

    Resume updateResumeById(Long resumeId, Resume resume);
    Education updateEducationById(Long educationId, Education education);
    Experience updateExperienceById(Long experienceId, Experience experience);

    public List<Skill> getAllSkills();
}
