package com.springframework.CareerConnect.bootstrap;

import com.springframework.CareerConnect.domain.*;
import com.springframework.CareerConnect.enums.Status;
import com.springframework.CareerConnect.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Set;

import java.time.LocalDateTime;

@Component
@ConditionalOnProperty(name = "bootstrapdata.enabled", havingValue = "true", matchIfMissing = true)
public class DataBootstrap implements CommandLineRunner {

    private final AddressRepository addressRepository;
    private final CompanyRepository companyRepository;
    private final EducationRepository educationRepository;
    private final JobPostingRepository jobPostingRepository;
    private final SchoolRepository schoolRepository;
    private final SkillRepository skillRepository;
    private final TagRepository tagRepository;
    private final UserRepository userRepository;

    public DataBootstrap(AddressRepository addressRepository,
                         CompanyRepository companyRepository,
                         EducationRepository educationRepository,
                         JobPostingRepository jobPostingRepository,
                         SchoolRepository schoolRepository,
                         SkillRepository skillRepository,
                         TagRepository tagRepository,
                         UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.companyRepository = companyRepository;
        this.educationRepository = educationRepository;
        this.jobPostingRepository = jobPostingRepository;
        this.schoolRepository = schoolRepository;
        this.skillRepository = skillRepository;
        this.tagRepository = tagRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        Skill javaSkill = Skill.builder().name("Java").build();
        Skill pythonSkill = Skill.builder().name("Python").build();
        Skill mlSkill = Skill.builder().name("Machine Learning").build();
        Skill cloudSkill = Skill.builder().name("Cloud Computing").build();
        skillRepository.saveAll(Set.of(javaSkill, pythonSkill, mlSkill, cloudSkill));

        Tag remoteTag = Tag.builder().tagName("Remote").build();
        Tag urgentTag = Tag.builder().tagName("Urgent").build();
        Tag fullTimeTag = Tag.builder().tagName("Full-Time").build();
        Tag backendTag = Tag.builder().tagName("Backend Development").build();
        tagRepository.saveAll(Set.of(remoteTag, urgentTag, fullTimeTag, backendTag));

        Company company = Company.builder().name("Tech Solutions Inc.").build();

        Address companyAddress = Address.builder()
                .street("789 Corporate Blvd")
                .city("San Francisco")
                .state("CA")
                .country("USA")
                .zipCode("94103")
                .build();

        company.setAddress(Set.of(companyAddress));
        addressRepository.save(companyAddress);
        companyRepository.save(company);

        User user1 = User.builder()
                .username("alice123")
                .name("Alice")
                .lastName("Johnson")
                .email("alice@mail.com")
                .password("hashedpassword1")
                .role("USER")
                .status(String.valueOf(Status.ACTIVE))
                .phone("123-456-789")
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .skill(Set.of(javaSkill))
                .skill(Set.of(pythonSkill))
                .build();

        User user2 = User.builder()
                .username("bob456")
                .name("Bob")
                .lastName("Smith")
                .email("bob@mail.com")
                .password("hashedpassword2")
                .role("USER")
                .status(String.valueOf(Status.ACTIVE))
                .phone("987-654-321")
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .skill(Set.of(pythonSkill))
                .build();

        userRepository.saveAll(Set.of(user1, user2));

        User user3 = User.builder()
                .username("carol789")
                .name("Carol")
                .lastName("Williams")
                .email("carol@mail.com")
                .password("hashedpassword3")
                .role("USER")
                .status(String.valueOf(Status.ACTIVE))
                .phone("456-789-123")
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .skill(Set.of(mlSkill, cloudSkill))
                .build();

        User user4 = User.builder()
                .username("dave012")
                .name("Dave")
                .lastName("Brown")
                .email("dave@mail.com")
                .password("hashedpassword4")
                .role("USER")
                .status(String.valueOf(Status.ACTIVE))
                .phone("321-654-987")
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .skill(Set.of(javaSkill, cloudSkill))
                .build();

        userRepository.saveAll(Set.of(user3, user4));

        JobPosting job = JobPosting.builder()
                .jobTitle("Software Engineer - Backend Specialist")
                .jobDescription("We are seeking a highly skilled Software Engineer to join our backend team. "
                        + "The ideal candidate will have expertise in Java, cloud computing, and experience in "
                        + "designing scalable backend systems. Responsibilities include developing APIs, "
                        + "collaborating with frontend teams, and deploying services to cloud environments.")
                .jobLocation("Remote")
                .company(company)
                .tag(Set.of(remoteTag, fullTimeTag, backendTag))
                .applicant(Set.of(user1, user2))
                .build();

        jobPostingRepository.save(job);

        JobPosting job2 = JobPosting.builder()
                .jobTitle("Data Scientist - Machine Learning Specialist")
                .jobDescription("We are looking for a talented Data Scientist with expertise in Machine Learning. "
                        + "The candidate will design and implement predictive models, analyze large datasets, "
                        + "and deploy machine learning solutions to production environments.")
                .jobLocation("New York")
                .company(company)
                .tag(Set.of(urgentTag, backendTag))
                .applicant(Set.of(user3))
                .build();

        JobPosting job3 = JobPosting.builder()
                .jobTitle("Cloud Engineer")
                .jobDescription("Join our team as a Cloud Engineer to design and maintain cloud-based infrastructure. "
                        + "The ideal candidate will have strong experience in cloud platforms, DevOps practices, "
                        + "and scalable system architecture.")
                .jobLocation("San Francisco")
                .company(company)
                .tag(Set.of(remoteTag, fullTimeTag, backendTag))
                .applicant(Set.of(user4))
                .build();

        jobPostingRepository.saveAll(Set.of(job2, job3));
    }
}