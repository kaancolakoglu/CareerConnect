package com.springframework.CareerConnect.bootstrap;

import com.springframework.CareerConnect.domain.*;
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
        // Create Skills
        Skill javaSkill = Skill.builder().name("Java").build();
        Skill pythonSkill = Skill.builder().name("Python").build();
        skillRepository.saveAll(Set.of(javaSkill, pythonSkill));

        // Create Tags
        Tag remoteTag = Tag.builder().tagName("Remote").build();
        Tag urgentTag = Tag.builder().tagName("Urgent").build();
        tagRepository.saveAll(Set.of(remoteTag, urgentTag));

        // Create Schools
        School school1 = School.builder().schoolName("Springfield High School").build();
        School school2 = School.builder().schoolName("Oakwood Elementary").build();
        schoolRepository.saveAll(Set.of(school1, school2));

        // Create Addresses
        Address address1 = Address.builder()
                .street("123 Main St")
                .city("Springfield")
                .state("IL")
                .country("USA")
                .zipCode("62704")
                .school(school1)
                .build();

        Address address2 = Address.builder()
                .street("456 Elm St")
                .city("Springfield")
                .state("IL")
                .country("USA")
                .zipCode("62705")
                .school(school2)
                .build();

        addressRepository.saveAll(Set.of(address1, address2));

        // Create Companies
        Company company1 = Company.builder().name("Tech Solutions").build();
        Company company2 = Company.builder().name("EduCorp").build();
        company1.setAddress(Set.of(address1));
        company2.setAddress(Set.of(address2));
        companyRepository.saveAll(Set.of(company1, company2));

        // Create Educations
        Education education1 = Education.builder()
                .educationName("Bachelor of Science")
                .degree("B.Sc.")
                .major("Computer Science")
                .startDate(LocalDateTime.of(2018, 9, 1, 0, 0))
                .endDate(LocalDateTime.of(2022, 6, 1, 0, 0))
                .build();

        Education education2 = Education.builder()
                .educationName("Master of Business Administration")
                .degree("MBA")
                .major("Marketing")
                .startDate(LocalDateTime.of(2020, 1, 1, 0, 0))
                .endDate(LocalDateTime.of(2022, 12, 1, 0, 0))
                .build();

        educationRepository.saveAll(Set.of(education1, education2));

        // Assign Educations to Schools
        school1.setEducation(Set.of(education1));
        school2.setEducation(Set.of(education2));
        schoolRepository.saveAll(Set.of(school1, school2));

        // Create Users
        User user1 = User.builder()
                .username("alice123")
                .name("Alice")
                .lastName("Johnson")
                .email("alice@mail.com")
                .password("hashedpassword1")
                .role("USER")
                .status("ACTIVE")
                .phone("123-456-789")
                .createdDate(org.joda.time.LocalDateTime.now())
                .updatedDate(org.joda.time.LocalDateTime.now())
                .skill(Set.of(javaSkill))
                .school(Set.of(school1))
                .company(company1)
                .address(Set.of(address1))
                .build();

        User user2 = User.builder()
                .username("bob456")
                .name("Bob")
                .lastName("Smith")
                .email("bob@mail.com")
                .password("hashedpassword2")
                .role("USER")
                .status("ACTIVE")
                .phone("987-654-321")
                .createdDate(org.joda.time.LocalDateTime.now())
                .updatedDate(org.joda.time.LocalDateTime.now())
                .skill(Set.of(pythonSkill))
                .school(Set.of(school2))
                .company(company2)
                .address(Set.of(address2))
                .build();

        userRepository.saveAll(Set.of(user1, user2));

        // Create Job Postings
        JobPosting job1 = JobPosting.builder()
                .jobTitle("Software Engineer")
                .jobDescription("Develop and maintain software applications.")
                .jobLocation("Remote")
                .company(company1)
                .tag(Set.of(remoteTag))
                .applicant(Set.of(user1))
                .build();

        JobPosting job2 = JobPosting.builder()
                .jobTitle("Data Scientist")
                .jobDescription("Analyze data and build predictive models.")
                .jobLocation("New York")
                .company(company2)
                .tag(Set.of(urgentTag))
                .applicant(Set.of(user2))
                .build();

        jobPostingRepository.saveAll(Set.of(job1, job2));
    }
}