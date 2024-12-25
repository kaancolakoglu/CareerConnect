package com.springframework.CareerConnect.services;

import com.springframework.CareerConnect.domain.*;
import com.springframework.CareerConnect.exceptions.ResourceNotFoundException;
import com.springframework.CareerConnect.exceptions.UnauthorizedAccessException;
import com.springframework.CareerConnect.repositories.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class ResumeServiceImpl implements ResumeService {
    private final ResumeRepository resumeRepository;
    private final EducationRepository educationRepository;
    private final ExperienceRepository experienceRepository;
    private final SkillRepository skillRepository;
    private final UserRepository userRepository;

    public ResumeServiceImpl(ResumeRepository resumeRepository,
                             EducationRepository educationRepository,
                             ExperienceRepository experienceRepository,
                             SkillRepository skillRepository,
                             UserRepository userRepository
    ) {
        this.resumeRepository = resumeRepository;
        this.educationRepository = educationRepository;
        this.experienceRepository = experienceRepository;
        this.skillRepository = skillRepository;
        this.userRepository = userRepository;
    }


    @Override
    @Transactional
    public Resume createResume(Long userId, Resume resume) {

        try {
            log.info("Finding user with ID: {}", userId);
            User user = userRepository.findById(userId).
                    orElseThrow(() -> new ResourceNotFoundException("User with id " + userId + " not found"));

            Resume newResume = new Resume();
            newResume.setResumeName(resume.getResumeName());
            newResume.setUser(user);

            log.info("Attempting to save resume: {}", newResume);

            Resume savedResume = resumeRepository.save(newResume);

            if (resume.getEducations() != null && !resume.getEducations().isEmpty()) {
                for (Education education : resume.getEducations()) {
                    addEducation(savedResume.getResumeId(), education);
                }
            }

            if (resume.getExperiences() != null && !resume.getExperiences().isEmpty()) {
                for (Experience experience : resume.getExperiences()) {
                    addExperience(savedResume.getResumeId(), experience);
                }
            }

            if (resume.getSkills() != null && !resume.getSkills().isEmpty()) {
                for (Skill skill : resume.getSkills()) {
                    addSkill(savedResume.getResumeId(), skill);
                }
            }

            user.getResumes().add(savedResume);
            userRepository.save(user);

            return savedResume;
        } catch (ResourceNotFoundException e) {
            log.error("Error creating resume", e);
            throw new RuntimeException("Error creating resume", e);
        }
    }

    @Override
    @Transactional
    public Resume addExperience(Long resumeId, Experience experience) {
        try {
            Resume resume = resumeRepository.findById(resumeId)
                    .orElseThrow(()->new ResourceNotFoundException("Resume with id " + resumeId + " not found"));

            experience.setResume(resume);
            Experience savedExperience = experienceRepository.save(experience);

            resume.getExperiences().add(savedExperience);

            return resumeRepository.save(resume);
        } catch (ResourceNotFoundException e) {
            log.error("Error adding experience to resume {}: {}", resumeId, e.getMessage());
            throw new RuntimeException("Failed to add experience", e);
        }
    }

    @Override
    @Transactional
    public Resume addEducation(Long resumeId, Education education) {
        try {
            Resume resume = resumeRepository.findById(resumeId)
                    .orElseThrow(()->new ResourceNotFoundException("Resume with id " + resumeId + " not found"));

            education.setResume(resume);
            Education savedEducation = educationRepository.save(education);

            resume.getEducations().add(savedEducation);

            return resumeRepository.save(resume);
        } catch (ResourceNotFoundException e) {
            log.error("Error adding education to resume {}: {}", resumeId, e.getMessage());
            throw new RuntimeException("Failed to add education", e);
        }
    }

    @Override
    @Transactional
    public Resume addSkill(Long resumeId, Skill skill) {
        try {
            Resume resume = resumeRepository.findById(resumeId)
                    .orElseThrow(() -> new ResourceNotFoundException("Resume with id " + resumeId + " not found"));

            // Handle potential duplicate skills
            String standardizedName = skill.getName().trim().toLowerCase();
            Skill skillToAdd = skillRepository.findByNameIgnoreCase(standardizedName)
                    .stream()
                    .findFirst()
                    .orElseGet(() -> {
                        skill.setName(standardizedName);
                        return skillRepository.save(skill);
                    });

            // Add skill if not already present
            if (!resume.getSkills().contains(skillToAdd)) {
                resume.getSkills().add(skillToAdd);
                return resumeRepository.save(resume);
            }

            return resume;
        } catch (Exception e) {
            log.error("Error adding skill to resume {}: {}", resumeId, e.getMessage());
            throw new RuntimeException("Failed to add skill", e);
        }
    }

    @Override
    @Transactional
    public void deleteResumeById(Long resumeId) {
        Resume resume = resumeRepository.findById(resumeId)
                .orElseThrow(()->new ResourceNotFoundException("Resume with id " + resumeId + " not found"));

        resumeRepository.delete(resume);
    }

    @Override
    @Transactional
    public void deleteExperienceById(Long resumeId, Long experienceId) {
        Resume resume = resumeRepository.findById(resumeId)
                .orElseThrow(()->new ResourceNotFoundException("Resume with id " + resumeId + " not found"));
        Experience experience = experienceRepository.findById(experienceId)
                .orElseThrow(() -> new ResourceNotFoundException("Experience with id " + experienceId + " not found"));

        if(!experience.getResume().getResumeId().equals(resumeId)) {
            throw new UnauthorizedAccessException("This experience entry doesn't belong to the specified resume");
        }

        resume.getExperiences().remove(experience);
        resumeRepository.save(resume);
    }

    @Override
    @Transactional
    public void deleteEducationById(Long resumeId, Long educationId) {
        Resume resume = resumeRepository.findById(resumeId)
                .orElseThrow(()->new ResourceNotFoundException("Resume with id " + resumeId + " not found"));

        Education education = educationRepository.findById(educationId)
                .orElseThrow(() -> new ResourceNotFoundException("Education with id " + educationId + " not found"));

        if (!education.getResume().getResumeId().equals(resumeId)) {
            throw new UnauthorizedAccessException("This education entry doesn't belong to the specified resume");
        }

        resume.getEducations().remove(education);
        resumeRepository.save(resume);

        educationRepository.delete(education);
    }

    @Override
    @Transactional
    public void deleteSkillById(Long resumeId, Long skillId) {
        Resume resume = resumeRepository.findById(resumeId)
                .orElseThrow(()->new ResourceNotFoundException("Resume with id " + resumeId + " not found"));
        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(()->new ResourceNotFoundException("Skill with id " + skillId + " not found"));

        if (!resume.getSkills().contains(skill)) {
            log.warn("Attempted to delete skill {} from resume {} but no such association exists", skillId, resumeId);
            throw new ResourceNotFoundException("Skill with id " + skillId + " isn't associated with this resume ");
        }

        resume.getSkills().remove(skill);
        skill.getResume().remove(resume);

        resumeRepository.save(resume);

        if (skill.getResume().isEmpty()) {
            log.debug("Skill {} has no more associated resumes, deleting the skill entity", skillId);
            skillRepository.delete(skill);
        }
        log.info("Skill {} deleted from resume {}", skillId, resumeId);
    }

    @Override
    @Transactional
    public Resume updateResumeById(Long resumeId, Resume resume) {
        try {
            Resume updatedResume = resumeRepository.findById(resumeId)
                    .orElseThrow(()->new ResourceNotFoundException("Resume with id " + resumeId + " not found"));

            updatedResume.setResumeName(resume.getResumeName());
            updatedResume.setSkills(resume.getSkills());
            updatedResume.setEducations(resume.getEducations());
            updatedResume.setExperiences(resume.getExperiences());
            return resumeRepository.save(updatedResume);
        } catch (Exception e) {
            log.error("Error updating resume {}: {}", resumeId, e.getMessage());
            throw new RuntimeException("Failed to update resume", e);
        }
    }

    @Override
    @Transactional
    public Education updateEducationById(Long educationId, Education education) {
        Education updatedEducation = educationRepository.findById(educationId)
                .orElseThrow(() -> new ResourceNotFoundException("Education with id " + educationId + " not found"));

        updatedEducation.setDegree(education.getDegree());
        updatedEducation.setStartDate(education.getStartDate());
        updatedEducation.setEndDate(education.getEndDate());
        updatedEducation.setMajor(education.getMajor());

        return educationRepository.save(updatedEducation);
    }

    @Override
    @Transactional
    public Experience updateExperienceById(Long experienceId, Experience experience) {

        Experience updatedExperience = experienceRepository.findById(experienceId)
                .orElseThrow(() -> new ResourceNotFoundException("Experience with id " + experienceId + " not found"));

        updatedExperience.setStartDate(experience.getStartDate());
        updatedExperience.setEndDate(experience.getEndDate());
        updatedExperience.setCompanyName(experience.getCompanyName());
        updatedExperience.setJobTitle(experience.getJobTitle());
        updatedExperience.setDescription(experience.getDescription());

        return experienceRepository.save(updatedExperience);
    }


}
