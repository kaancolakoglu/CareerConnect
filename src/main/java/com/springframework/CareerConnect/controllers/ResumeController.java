package com.springframework.CareerConnect.controllers;

import com.springframework.CareerConnect.Mapper.MapStructMapper;
import com.springframework.CareerConnect.domain.Education;
import com.springframework.CareerConnect.domain.Experience;
import com.springframework.CareerConnect.domain.Resume;
import com.springframework.CareerConnect.domain.Skill;
import com.springframework.CareerConnect.model.ResumeDTO;
import com.springframework.CareerConnect.repositories.ResumeRepository;
import com.springframework.CareerConnect.services.ResumeService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class ResumeController {

    private final ResumeService resumeService;
    private final ResumeRepository resumeRepository;
    private final MapStructMapper mapStructMapper;

    public ResumeController(ResumeService resumeService, ResumeRepository resumeRepository, MapStructMapper mapStructMapper) {
        this.resumeService = resumeService;
        this.resumeRepository = resumeRepository;
        this.mapStructMapper = mapStructMapper;
    }

    @GetMapping("/resumes/{resumeId}")
    public ResponseEntity<ResumeDTO> getResume(@PathVariable Long resumeId) {
        log.info("Retrieving resume with ID {}", resumeId);
        Resume resume = resumeRepository.findById(resumeId)
                .orElseThrow(() -> new RuntimeException("Could not find resume with ID " + resumeId));
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



}
