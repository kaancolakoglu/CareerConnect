package com.springframework.CareerConnect.bootstrap;

import com.springframework.CareerConnect.domain.*;
import com.springframework.CareerConnect.enums.ERole;
import com.springframework.CareerConnect.enums.Status;
import com.springframework.CareerConnect.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@Component
@ConditionalOnProperty(name = "bootstrapdata.enabled", havingValue = "true", matchIfMissing = true)
public class DataBootstrap implements CommandLineRunner {

    private final AddressRepository addressRepository;
    private final CompanyRepository companyRepository;
    private final EducationRepository educationRepository;
    private final JobPostingRepository jobPostingRepository;
    private final ResumeRepository resumeRepository;
    private final ExperienceRepository experienceRepository;
    private final SkillRepository skillRepository;
    private final TagRepository tagRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public DataBootstrap(AddressRepository addressRepository,
                         CompanyRepository companyRepository,
                         EducationRepository educationRepository,
                         JobPostingRepository jobPostingRepository,
                         ResumeRepository resumeRepository,
                         ExperienceRepository experienceRepository,
                         SkillRepository skillRepository,
                         TagRepository tagRepository,
                         UserRepository userRepository,
                         RoleRepository roleRepository
    ) {
        this.addressRepository = addressRepository;
        this.companyRepository = companyRepository;
        this.educationRepository = educationRepository;
        this.jobPostingRepository = jobPostingRepository;
        this.resumeRepository = resumeRepository;
        this.experienceRepository = experienceRepository;
        this.skillRepository = skillRepository;
        this.tagRepository = tagRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        // Initialize Skills
        Skill javaSkill = Skill.builder().name("Java").build();
        Skill pythonSkill = Skill.builder().name("Python").build();
        Skill mlSkill = Skill.builder().name("Machine Learning").build();
        Skill cloudSkill = Skill.builder().name("Cloud Computing").build();
        skillRepository.saveAll(new HashSet<>(Arrays.asList(javaSkill, pythonSkill, mlSkill, cloudSkill)));

        // Initialize Tags
        Tag remoteTag = Tag.builder().tagName("Remote").build();
        Tag urgentTag = Tag.builder().tagName("Urgent").build();
        Tag fullTimeTag = Tag.builder().tagName("Full-Time").build();
        Tag backendTag = Tag.builder().tagName("Backend Development").build();
        tagRepository.saveAll(new HashSet<>(Arrays.asList(remoteTag, urgentTag, fullTimeTag, backendTag)));

        // Initialize Roles
        Role roleAdmin = Role.builder().name(ERole.ROLE_ADMIN).build();
        Role roleUser = Role.builder().name(ERole.ROLE_USER).build();
        Role roleCompany = Role.builder().name(ERole.ROLE_COMPANY).build();
        roleRepository.saveAll(new HashSet<>(Arrays.asList(roleAdmin, roleUser, roleCompany)));

        // Create and save Resume
        Resume resume1 = Resume.builder()
                .resumeName("Resume 1")
                .skills(new HashSet<>(Arrays.asList(javaSkill, cloudSkill)))
                .educations(new HashSet<>())
                .experiences(new HashSet<>())
                .build();

        resume1 = resumeRepository.save(resume1);

        Education education1 = Education.builder()
                .degree("Bachelor's")
                .major("Computer Science")
                .schoolName("Example University")
                .startDate(LocalDateTime.now().minusYears(4))
                .endDate(LocalDateTime.now().minusYears(1))
                .resume(resume1)
                .build();

        education1 = educationRepository.save(education1);

        Experience experience1 = Experience.builder()
                .companyName("Tech Corp")
                .description("Developed backend services")
                .jobTitle("Backend Developer")
                .startDate(LocalDateTime.now().minusYears(1))
                .endDate(LocalDateTime.now())
                .resume(resume1)
                .build();

        experience1 = experienceRepository.save(experience1);

        resume1.setEducations(new HashSet<>(Collections.singletonList(education1)));
        resume1.setExperiences(new HashSet<>(Collections.singletonList(experience1)));

        Resume savedResume1 = resumeRepository.save(resume1);

        // Initialize Company
        Company company = Company.builder().name("Tech Solutions Inc.").build();
        Address companyAddress = Address.builder()
                .street("789 Corporate Blvd")
                .city("San Francisco")
                .state("CA")
                .country("USA")
                .zipCode("94103")
                .build();
        company.setAddressId(new HashSet<>(Collections.singletonList(companyAddress)));
        addressRepository.save(companyAddress);
        companyRepository.save(company);

        // Initialize Users
        User user1 = User.builder()
                .username("alice123")
                .name("Alice")
                .lastName("Johnson")
                .email("alice@mail.com")
                .password("hashedpassword1") // Replace with actual hashed password
                .roles(new HashSet<>(Arrays.asList(roleUser)))
                .status(String.valueOf(Status.ACTIVE))
                .phoneNumber("123-456-789")
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .resumes(new HashSet<>(Collections.singletonList(resume1)))
                .build();

        User user2 = User.builder()
                .username("bob456")
                .name("Bob")
                .lastName("Smith")
                .email("bob@mail.com")
                .password("hashedpassword2") // Replace with actual hashed password
                .roles(new HashSet<>(Arrays.asList(roleUser)))
                .status(String.valueOf(Status.ACTIVE))
                .phoneNumber("987-654-321")
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .resumes(new HashSet<>(Collections.singletonList(resume1)))
                .build();

        userRepository.saveAll(new HashSet<>(Arrays.asList(user1, user2)));

        // Initialize Job Postings
        JobPosting job1 = JobPosting.builder()
                .jobTitle("Backend Engineer")
                .jobDescription("Develop APIs and microservices.")
                .jobLocation("Remote")
                .company(company)
                .tag(new HashSet<>(Arrays.asList(backendTag,urgentTag,fullTimeTag)))
                .applicant(new HashSet<>(Arrays.asList(user1, user2)))
                .build();

        JobPosting job2 = JobPosting.builder()
                .jobTitle("Data Scientist")
                .jobDescription("Analyze data and build ML models.")
                .jobLocation("New York")
                .company(company)
                .tag(new HashSet<>(Arrays.asList(remoteTag)))
                .applicant(new HashSet<>(Arrays.asList(user2)))
                .build();

        jobPostingRepository.saveAll(new HashSet<>(Arrays.asList(job1, job2)));
    }
}