package com.springframework.CareerConnect.controllers;

import com.springframework.CareerConnect.Mapper.MapStructMapper;
import com.springframework.CareerConnect.domain.*;
import com.springframework.CareerConnect.exceptions.ResourceNotFoundException;
import com.springframework.CareerConnect.exceptions.UnauthorizedAccessException;
import com.springframework.CareerConnect.model.EducationDTO;
import com.springframework.CareerConnect.model.ExperienceDTO;
import com.springframework.CareerConnect.model.ResumeDTO;
import com.springframework.CareerConnect.model.SkillDTO;
import com.springframework.CareerConnect.repositories.EducationRepository;
import com.springframework.CareerConnect.repositories.ExperienceRepository;
import com.springframework.CareerConnect.repositories.ResumeRepository;
import com.springframework.CareerConnect.repositories.UserRepository;
import com.springframework.CareerConnect.services.ResumeService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class ResumeController {

    private final ResumeService resumeService;
    private final ResumeRepository resumeRepository;
    private final MapStructMapper mapStructMapper;
    private final EducationRepository educationRepository;
    private final ExperienceRepository experienceRepository;
    private final UserRepository userRepository;

    public ResumeController(ResumeService resumeService, ResumeRepository resumeRepository, MapStructMapper mapStructMapper, EducationRepository educationRepository, ExperienceRepository experienceRepository, UserRepository userRepository) {
        this.resumeService = resumeService;
        this.resumeRepository = resumeRepository;
        this.mapStructMapper = mapStructMapper;
        this.educationRepository = educationRepository;
        this.experienceRepository = experienceRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/resume/{resumeId}")
    public ResponseEntity<ResumeDTO> getResumeById(@PathVariable Long resumeId) {
        log.info("Retrieving resume with ID {}", resumeId);
        Resume resume = resumeRepository.findById(resumeId)
                .orElseThrow(() -> new RuntimeException("Could not find resume for user with ID " + resumeId));
        ResumeDTO resumeDTO = mapStructMapper.toResumeDTO(resume);

        return new ResponseEntity<>(resumeDTO, HttpStatus.OK);
    }

    @GetMapping("/resumes/{userId}")
    public ResponseEntity<ResumeDTO> getResumeByUserId(@PathVariable Long userId) {
        log.info("Retrieving resume with user ID {}", userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Could not find user with ID " + userId));

        Resume resume =  user.getResumes().stream().findFirst().orElseThrow(() -> new RuntimeException("Could not find resume for user with ID " + userId));

        ResumeDTO resumeDTO = mapStructMapper.toResumeDTO(resume);

        return new ResponseEntity<>(resumeDTO, HttpStatus.OK);
    }

    @PostMapping("/resumes")
    public ResponseEntity<ResumeDTO> createResume(
            @RequestParam Long userId,
            @Valid @RequestBody Resume resume) {
        log.info("Creating a new resume for user with ID {}", userId);

        try {
            Resume createdResume = resumeService.createResume(userId, resume);

            ResumeDTO resumeDTO = mapStructMapper.toResumeDTO(createdResume);
            return new ResponseEntity<>(resumeDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error creating a resume: {}", e.getMessage());
            throw new RuntimeException("Failed to create a resume");
        }
    }
    //TODO: Fix these controllers.
    @PutMapping("/resumes/{resumeId}")
    public ResponseEntity<ResumeDTO> updateResume(@PathVariable Long resumeId, @RequestBody Resume resume) {
        log.info("Updating resume with ID {}", resumeId);

        Resume updatedResume = resumeService.updateResumeById(resumeId, resume);

        ResumeDTO resumeDTO = mapStructMapper.toResumeDTO(updatedResume);

        return new ResponseEntity<>(resumeDTO, HttpStatus.OK);
    }

    @DeleteMapping("/resumes/{resumeId}")
    public ResponseEntity<Void> deleteResume(@PathVariable Long resumeId) {
        log.info("Deleting resume with ID {}", resumeId);
        try {
            resumeService.deleteResumeById(resumeId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            log.error("Could not find resume with ID {}", resumeId);
            throw e;
        } catch (UnauthorizedAccessException e) {
            log.error("Unauthorized access for resume with ID {}", resumeId);
            throw e;
        } catch (Exception e) {
            log.error("Error deleting resume: {}", e.getMessage());
            throw e;
        }
    }


    @PostMapping("/resumes/{resumeId}/education")
    public ResponseEntity<ResumeDTO> addEducation(@PathVariable Long resumeId,
                                               @Valid @RequestBody Education education) {
        log.info("Adding education to resume: {}", resumeId);
        try {
            Resume updatedResume = resumeService.addEducation(resumeId, education);

            ResumeDTO resumeDTO = mapStructMapper.toResumeDTO(updatedResume);

            return ResponseEntity.ok(resumeDTO);
        } catch (Exception e) {
            log.error("Error adding education: {}", e.getMessage());
            throw new RuntimeException("Failed to add education {}", e);
        }
    }

    //TODO: Fix these controllers.
    @PutMapping("/resumes/{resumeId}/education/{educationId}")
    public ResponseEntity<EducationDTO> updateEducation(@PathVariable Long educationId, @RequestBody Education education) {
        log.info("Updating education with id {}", educationId);

        Education updatedEducation = resumeService.updateEducationById(educationId, education);

        EducationDTO educationDTO = mapStructMapper.toDto(updatedEducation);

        return new ResponseEntity<>(educationDTO, HttpStatus.OK);
    }

    @DeleteMapping("/resumes/{resumeId}/education/{educationId}")
    public ResponseEntity<Void> deleteEducation(@PathVariable Long resumeId, @PathVariable Long educationId) {
        log.info("Deleting education with ID {}", educationId);
        try {
            resumeService.deleteEducationById(resumeId, educationId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            log.error("Could not find education with ID {}", educationId);
            throw e;
        } catch (UnauthorizedAccessException e) {
            log.error("Unauthorized access for education with ID {}", educationId);
            throw e;
        } catch (Exception e) {
            log.error("Error deleting education: {}", e.getMessage());
            throw e;
        }
    }


    @PostMapping("/resumes/{resumeId}/experience")
    public ResponseEntity<ResumeDTO> addExperience(@PathVariable Long resumeId,
                                                @Valid @RequestBody Experience experience) {
        log.info("Adding experience to resume: {}", resumeId);
        try {
            Resume updatedResume = resumeService.addExperience(resumeId, experience);

            ResumeDTO resumeDTO = mapStructMapper.toResumeDTO(updatedResume);
            return ResponseEntity.ok(resumeDTO);
        } catch (Exception e) {
            log.error("Error adding experience: {}", e.getMessage());
            throw new RuntimeException("Failed to add experience {}", e);
        }
    }

    //TODO: Fix these controllers.
    @PutMapping("/resumes/{resumeId}/experience/{experienceId}")
    public ResponseEntity<ExperienceDTO> updateExperience(@PathVariable Long experienceId, @RequestBody Experience experience) {

        log.info("Updating experience with id {}", experienceId);

        Experience updatedExperience = resumeService.updateExperienceById(experienceId, experience);

        ExperienceDTO experienceDTO = mapStructMapper.toDto(updatedExperience);

        return new ResponseEntity<>(experienceDTO, HttpStatus.OK);
    }

    @DeleteMapping("/resumes/{resumeId}/experience/{experienceId}")
    public ResponseEntity<Void> deleteExperience(@PathVariable Long resumeId,Long experienceId) {
        log.info("Deleting experience with ID {}", experienceId);
        try {
            resumeService.deleteExperienceById(resumeId,experienceId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            log.error("Could not find experience with ID {}", experienceId);
            throw e;
        } catch (UnauthorizedAccessException e) {
            log.error("Unauthorized access for experience with ID {}", experienceId);
            throw e;
        } catch (Exception e) {
            log.error("Error deleting experience: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/skills")
    public ResponseEntity<List<SkillDTO>> getSkills() {
        log.info("Retrieving all skills");

        return ResponseEntity.ok(resumeService.getAllSkills().stream().map(mapStructMapper::toDto).collect(Collectors.toList()));
    }

    @PostMapping("/resumes/{resumeId}/skill")
    public ResponseEntity<ResumeDTO> addSkill(@PathVariable Long resumeId,
                                             @Valid @RequestBody Skill skill) {
        log.info("Adding skill to resume: {}", resumeId);
        try {
            Resume updatedResume = resumeService.addSkill(resumeId, skill);
            ResumeDTO resumeDTO = mapStructMapper.toResumeDTO(updatedResume);

            return ResponseEntity.ok(resumeDTO);
        } catch (Exception e) {
            log.error("Error adding skill: {}", e.getMessage());
            throw new RuntimeException("Failed to add skill {}", e);
        }
    }

    @DeleteMapping("resumes/{resumeId}/skill/{skillId}")
    public ResponseEntity<Void> deleteSkill(@PathVariable Long resumeId, @PathVariable Long skillId) {
        log.info("Deleting skill with ID {}", skillId);
        try {
            resumeService.deleteSkillById(resumeId,skillId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            log.error("Could not find skill with ID {}", skillId);
            throw e;
        } catch (UnauthorizedAccessException e) {
            log.error("Unauthorized access for skill with ID {}", skillId);
            throw e;
        } catch (Exception e) {
            log.error("Error deleting skill: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
