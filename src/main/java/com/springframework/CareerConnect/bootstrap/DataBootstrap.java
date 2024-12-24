package com.springframework.CareerConnect.bootstrap;

import com.springframework.CareerConnect.domain.*;
import com.springframework.CareerConnect.enums.ERole;
import com.springframework.CareerConnect.enums.JobPostingStatus;
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
import java.util.Set;

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
        Skill javaSkill = Skill.builder().name("Java").build();
        Skill pythonSkill = Skill.builder().name("Python").build();
        Skill mlSkill = Skill.builder().name("Machine Learning").build();
        Skill cloudSkill = Skill.builder().name("Cloud Computing").build();
        Skill AWSSkill = Skill.builder().name("AWS").build();
        Skill dockerSkill = Skill.builder().name("Docker").build();
        Skill kubernetesSkill = Skill.builder().name("Kubernetes").build();
        Skill devOpsSkill = Skill.builder().name("DevOps").build();
        Skill sqlSkill = Skill.builder().name("SQL").build();
        Skill noSqlSkill = Skill.builder().name("NoSQL").build();
        Skill gitSkill = Skill.builder().name("Git").build();
        Skill springSkill = Skill.builder().name("Spring Boot").build();
        Skill hibernateSkill = Skill.builder().name("Hibernate").build();
        Skill dataAnalysisSkill = Skill.builder().name("Data Analysis").build();
        Skill restApiSkill = Skill.builder().name("REST API").build();
        Skill microservicesSkill = Skill.builder().name("Microservices").build();
        Skill azureSkill = Skill.builder().name("Azure").build();
        Skill gcpSkill = Skill.builder().name("Google Cloud Platform").build();
        Skill kafkaSkill = Skill.builder().name("Apache Kafka").build();
        Skill ciCdSkill = Skill.builder().name("CI/CD Pipelines").build();
        Skill terraformSkill = Skill.builder().name("Terraform").build();
        Skill ansibleSkill = Skill.builder().name("Ansible").build();
        Skill linuxSkill = Skill.builder().name("Linux").build();
        Skill bashSkill = Skill.builder().name("Bash Scripting").build();
        Skill postgresqlSkill = Skill.builder().name("PostgreSQL").build();
        Skill mongodbSkill = Skill.builder().name("MongoDB").build();
        Skill elasticsearchSkill = Skill.builder().name("Elasticsearch").build();
        Skill bigDataSkill = Skill.builder().name("Big Data").build();
        Skill neuralNetworksSkill = Skill.builder().name("Neural Networks").build();
        Skill nlpSkill = Skill.builder().name("Natural Language Processing").build();
        Skill computerVisionSkill = Skill.builder().name("Computer Vision").build();
        Skill tensorflowSkill = Skill.builder().name("TensorFlow").build();
        Skill pytorchSkill = Skill.builder().name("PyTorch").build();
        Skill reactSkill = Skill.builder().name("React").build();
        Skill angularSkill = Skill.builder().name("Angular").build();
        Skill vueSkill = Skill.builder().name("Vue.js").build();
        Skill htmlCssSkill = Skill.builder().name("HTML/CSS").build();
        Skill javascriptSkill = Skill.builder().name("JavaScript").build();
        Skill nodejsSkill = Skill.builder().name("Node.js").build();
        Skill graphqlSkill = Skill.builder().name("GraphQL").build();
        Skill flutterSkill = Skill.builder().name("Flutter").build();
        Skill androidSkill = Skill.builder().name("Android Development").build();
        Skill iosSkill = Skill.builder().name("iOS Development").build();
        Skill kotlinSkill = Skill.builder().name("Kotlin").build();
        Skill swiftSkill = Skill.builder().name("Swift").build();
        Skill cSharpSkill = Skill.builder().name("C#").build();
        Skill cppSkill = Skill.builder().name("C++").build();
        Skill goSkill = Skill.builder().name("Go").build();
        Skill rustSkill = Skill.builder().name("Rust").build();
        Skill rSkill = Skill.builder().name("R").build();


        skillRepository.saveAll(new HashSet<>(Arrays.asList(javaSkill,
                pythonSkill, mlSkill, cloudSkill,AWSSkill,dockerSkill, kubernetesSkill, devOpsSkill, sqlSkill, noSqlSkill,
                gitSkill,springSkill, hibernateSkill, dataAnalysisSkill, restApiSkill, microservicesSkill
                ,azureSkill, gcpSkill, kafkaSkill, ciCdSkill, terraformSkill, ansibleSkill, linuxSkill, bashSkill,
                postgresqlSkill, mongodbSkill, elasticsearchSkill, bigDataSkill, neuralNetworksSkill, nlpSkill,
                computerVisionSkill, tensorflowSkill, pytorchSkill, reactSkill, angularSkill, vueSkill ,
                htmlCssSkill, javascriptSkill, nodejsSkill, graphqlSkill, flutterSkill, androidSkill, iosSkill, kotlinSkill,
                swiftSkill, cSharpSkill, cppSkill, goSkill, rustSkill, rSkill)));




        Tag remoteTag = Tag.builder().tagName("Remote").build();
        Tag urgentTag = Tag.builder().tagName("Urgent").build();
        Tag fullTimeTag = Tag.builder().tagName("Full-Time").build();
        Tag backendTag = Tag.builder().tagName("Backend Development").build();
        Tag frontendTag = Tag.builder().tagName("Frontend Development").build();
        Tag partTimeTag = Tag.builder().tagName("Part-Time").build();
        Tag contractTag = Tag.builder().tagName("Contract").build();
        Tag internTag = Tag.builder().tagName("Internship").build();
        Tag devOpsTag = Tag.builder().tagName("DevOps").build();
        Tag aiTag = Tag.builder().tagName("Artificial Intelligence").build();
        Tag mlTag = Tag.builder().tagName("Machine Learning").build();
        Tag dataScienceTag = Tag.builder().tagName("Data Science").build();
        Tag cloudTag = Tag.builder().tagName("Cloud Computing").build();
        Tag javaTag = Tag.builder().tagName("Java").build();
        Tag pythonTag = Tag.builder().tagName("Python").build();
        Tag urgentHireTag = Tag.builder().tagName("Urgent Hire").build();
        Tag remoteWorkTag = Tag.builder().tagName("Remote Work").build();
        Tag fullStackTag = Tag.builder().tagName("Full-Stack").build();
        Tag databaseTag = Tag.builder().tagName("Database Management").build();
        Tag springBootTag = Tag.builder().tagName("Spring Boot").build();
        Tag apiDevelopmentTag = Tag.builder().tagName("API Development").build();
        Tag cybersecurityTag = Tag.builder().tagName("Cybersecurity").build();
        Tag blockchainTag = Tag.builder().tagName("Blockchain").build();
        Tag fintechTag = Tag.builder().tagName("Fintech").build();
        Tag healthcareTag = Tag.builder().tagName("Healthcare").build();
        Tag ecommerceTag = Tag.builder().tagName("E-commerce").build();

        tagRepository.saveAll(new HashSet<>(Arrays.asList(
                remoteTag, urgentTag, fullTimeTag, backendTag, frontendTag, partTimeTag, contractTag, internTag,
                devOpsTag, aiTag, mlTag, dataScienceTag, cloudTag, javaTag, pythonTag, urgentHireTag,
                remoteWorkTag, fullStackTag, databaseTag, springBootTag, apiDevelopmentTag, cybersecurityTag,
                blockchainTag, fintechTag, healthcareTag, ecommerceTag
        )));



        // Create and save Resume
        Resume resume1 = Resume.builder()
                .resumeName("Resume 1")
                .skills(new HashSet<>(Arrays.asList(javaSkill, cloudSkill, pythonSkill, mlSkill)))
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

        Education education2 = Education.builder()
                .degree("Master's")
                .major("Artificial Intelligence")
                .schoolName("AI Institute")
                .startDate(LocalDateTime.now().minusYears(2))
                .endDate(LocalDateTime.now())
                .resume(resume1)
                .build();

        education2 = educationRepository.save(education2);

        Education education3 = Education.builder()
                .degree("Certification")
                .major("Cloud Computing")
                .schoolName("Online Academy")
                .startDate(LocalDateTime.now().minusYears(1))
                .endDate(LocalDateTime.now().minusMonths(6))
                .resume(resume1)
                .build();

        education3 = educationRepository.save(education3);

        Experience experience1 = Experience.builder()
                .companyName("Tech Corp")
                .description("Developed backend services")
                .jobTitle("Backend Developer")
                .startDate(LocalDateTime.now().minusYears(1))
                .endDate(LocalDateTime.now())
                .resume(resume1)
                .build();

        experience1 = experienceRepository.save(experience1);

        Experience experience2 = Experience.builder()
                .companyName("Cloud Solutions Inc.")
                .description("Designed and implemented cloud-based architectures")
                .jobTitle("Cloud Engineer")
                .startDate(LocalDateTime.now().minusYears(3))
                .endDate(LocalDateTime.now().minusYears(1))
                .resume(resume1)
                .build();

        experience2 = experienceRepository.save(experience2);

        Experience experience3 = Experience.builder()
                .companyName("AI Innovations")
                .description("Developed machine learning models for predictive analytics")
                .jobTitle("Machine Learning Engineer")
                .startDate(LocalDateTime.now().minusYears(2))
                .endDate(LocalDateTime.now())
                .resume(resume1)
                .build();

        experience3 = experienceRepository.save(experience3);

        Experience experience4 = Experience.builder()
                .companyName("Open Source Community")
                .description("Contributed to open-source projects focused on cloud and backend technologies")
                .jobTitle("Open Source Contributor")
                .startDate(LocalDateTime.now().minusYears(4))
                .endDate(LocalDateTime.now().minusYears(3))
                .resume(resume1)
                .build();

        experience4 = experienceRepository.save(experience4);

        resume1.setEducations(new HashSet<>(Arrays.asList(education1, education2, education3)));
        resume1.setExperiences(new HashSet<>(Arrays.asList(experience1, experience2, experience3, experience4)));

        Resume savedResume1 = resumeRepository.save(resume1);



        // Create and save Resume 2
        Resume resume2 = Resume.builder()
                .resumeName("Resume 2")
                .skills(new HashSet<>(Arrays.asList(pythonSkill, mlSkill, cloudSkill)))
                .educations(new HashSet<>())
                .experiences(new HashSet<>())
                .build();

        resume2 = resumeRepository.save(resume2);

        Education education4 = Education.builder()
                .degree("Bachelor's")
                .major("Data Science")
                .schoolName("Data University")
                .startDate(LocalDateTime.now().minusYears(5))
                .endDate(LocalDateTime.now().minusYears(2))
                .resume(resume2)
                .build();

        education4 = educationRepository.save(education4);

        Education education5 = Education.builder()
                .degree("Master's")
                .major("Machine Learning")
                .schoolName("AI and Data Science Institute")
                .startDate(LocalDateTime.now().minusYears(2))
                .endDate(LocalDateTime.now())
                .resume(resume2)
                .build();

        education5 = educationRepository.save(education5);

        Experience experience5 = Experience.builder()
                .companyName("DataWorks")
                .description("Analyzed large datasets and created predictive models")
                .jobTitle("Data Scientist")
                .startDate(LocalDateTime.now().minusYears(3))
                .endDate(LocalDateTime.now())
                .resume(resume2)
                .build();

        experience5 = experienceRepository.save(experience5);

        Experience experience6 = Experience.builder()
                .companyName("AI Startups")
                .description("Developed AI solutions for client use cases")
                .jobTitle("AI Engineer")
                .startDate(LocalDateTime.now().minusYears(2))
                .endDate(LocalDateTime.now())
                .resume(resume2)
                .build();

        experience6 = experienceRepository.save(experience6);

        resume2.setEducations(new HashSet<>(Arrays.asList(education4, education5)));
        resume2.setExperiences(new HashSet<>(Arrays.asList(experience5, experience6)));

        Resume savedResume2 = resumeRepository.save(resume2);

// Create and save Resume 3
        Resume resume3 = Resume.builder()
                .resumeName("Resume 3")
                .skills(new HashSet<>(Arrays.asList(javaSkill, dockerSkill, kubernetesSkill, devOpsSkill)))
                .educations(new HashSet<>())
                .experiences(new HashSet<>())
                .build();

        resume3 = resumeRepository.save(resume3);

        Education education6 = Education.builder()
                .degree("Bachelor's")
                .major("Software Engineering")
                .schoolName("Tech Academy")
                .startDate(LocalDateTime.now().minusYears(4))
                .endDate(LocalDateTime.now())
                .resume(resume3)
                .build();

        education6 = educationRepository.save(education6);

        Experience experience7 = Experience.builder()
                .companyName("DevOps Solutions")
                .description("Managed CI/CD pipelines and Kubernetes clusters")
                .jobTitle("DevOps Engineer")
                .startDate(LocalDateTime.now().minusYears(2))
                .endDate(LocalDateTime.now())
                .resume(resume3)
                .build();

        experience7 = experienceRepository.save(experience7);

        Experience experience8 = Experience.builder()
                .companyName("Tech Innovators")
                .description("Built containerized applications using Docker and Kubernetes")
                .jobTitle("Software Engineer")
                .startDate(LocalDateTime.now().minusYears(3))
                .endDate(LocalDateTime.now().minusYears(1))
                .resume(resume3)
                .build();

        experience8 = experienceRepository.save(experience8);

        resume3.setEducations(new HashSet<>(Collections.singletonList(education6)));
        resume3.setExperiences(new HashSet<>(Arrays.asList(experience7, experience8)));

        Resume savedResume3 = resumeRepository.save(resume3);

// Create and save Resume 4
        Resume resume4 = Resume.builder()
                .resumeName("Resume 4")
                .skills(new HashSet<>(Arrays.asList(mlSkill, tensorflowSkill, pythonSkill)))
                .educations(new HashSet<>())
                .experiences(new HashSet<>())
                .build();

        resume4 = resumeRepository.save(resume4);

        Education education7 = Education.builder()
                .degree("PhD")
                .major("Artificial Intelligence")
                .schoolName("Elite AI University")
                .startDate(LocalDateTime.now().minusYears(6))
                .endDate(LocalDateTime.now().minusYears(1))
                .resume(resume4)
                .build();

        education7 = educationRepository.save(education7);

        Experience experience9 = Experience.builder()
                .companyName("Future AI Labs")
                .description("Developed advanced neural networks and AI solutions")
                .jobTitle("Research Scientist")
                .startDate(LocalDateTime.now().minusYears(5))
                .endDate(LocalDateTime.now())
                .resume(resume4)
                .build();

        experience9 = experienceRepository.save(experience9);

        resume4.setEducations(new HashSet<>(Collections.singletonList(education7)));
        resume4.setExperiences(new HashSet<>(Collections.singletonList(experience9)));

        Resume savedResume4 = resumeRepository.save(resume4);


        // Create and save Resume 5
        Resume resume5 = Resume.builder()
                .resumeName("Resume 5")
                .skills(new HashSet<>(Arrays.asList(springSkill, hibernateSkill, restApiSkill)))
                .educations(new HashSet<>())
                .experiences(new HashSet<>())
                .build();

        resume5 = resumeRepository.save(resume5);

        Education education8 = Education.builder()
                .degree("Bachelor's")
                .major("Information Technology")
                .schoolName("Global IT University")
                .startDate(LocalDateTime.now().minusYears(4))
                .endDate(LocalDateTime.now())
                .resume(resume5)
                .build();

        education8 = educationRepository.save(education8);

        Experience experience10 = Experience.builder()
                .companyName("Enterprise Solutions")
                .description("Built RESTful APIs using Spring Boot and Hibernate")
                .jobTitle("Software Developer")
                .startDate(LocalDateTime.now().minusYears(2))
                .endDate(LocalDateTime.now())
                .resume(resume5)
                .build();

        experience10 = experienceRepository.save(experience10);

        Experience experience11 = Experience.builder()
                .companyName("Tech Consultants")
                .description("Designed and implemented database schemas")
                .jobTitle("Database Engineer")
                .startDate(LocalDateTime.now().minusYears(3))
                .endDate(LocalDateTime.now().minusYears(1))
                .resume(resume5)
                .build();

        experience11 = experienceRepository.save(experience11);

        resume5.setEducations(new HashSet<>(Collections.singletonList(education8)));
        resume5.setExperiences(new HashSet<>(Arrays.asList(experience10, experience11)));

        Resume savedResume5 = resumeRepository.save(resume5);

// Create and save Resume 6
        Resume resume6 = Resume.builder()
                .resumeName("Resume 6")
                .skills(new HashSet<>(Arrays.asList(kafkaSkill, ciCdSkill, cloudSkill)))
                .educations(new HashSet<>())
                .experiences(new HashSet<>())
                .build();

        resume6 = resumeRepository.save(resume6);

        Education education9 = Education.builder()
                .degree("Master's")
                .major("Cloud Computing")
                .schoolName("Cloud University")
                .startDate(LocalDateTime.now().minusYears(3))
                .endDate(LocalDateTime.now())
                .resume(resume6)
                .build();

        education9 = educationRepository.save(education9);

        Experience experience12 = Experience.builder()
                .companyName("Streaming Systems Inc.")
                .description("Managed Kafka pipelines for real-time data processing")
                .jobTitle("Data Streaming Engineer")
                .startDate(LocalDateTime.now().minusYears(2))
                .endDate(LocalDateTime.now())
                .resume(resume6)
                .build();

        experience12 = experienceRepository.save(experience12);

        Experience experience13 = Experience.builder()
                .companyName("Cloud Architects")
                .description("Set up CI/CD pipelines for microservices architecture")
                .jobTitle("DevOps Engineer")
                .startDate(LocalDateTime.now().minusYears(3))
                .endDate(LocalDateTime.now().minusYears(1))
                .resume(resume6)
                .build();

        experience13 = experienceRepository.save(experience13);

        resume6.setEducations(new HashSet<>(Collections.singletonList(education9)));
        resume6.setExperiences(new HashSet<>(Arrays.asList(experience12, experience13)));

        Resume savedResume6 = resumeRepository.save(resume6);

// Create and save Resume 7
        Resume resume7 = Resume.builder()
                .resumeName("Resume 7")
                .skills(new HashSet<>(Arrays.asList(htmlCssSkill, javascriptSkill, reactSkill)))
                .educations(new HashSet<>())
                .experiences(new HashSet<>())
                .build();

        resume7 = resumeRepository.save(resume7);

        Education education10 = Education.builder()
                .degree("Bachelor's")
                .major("Web Development")
                .schoolName("Creative Tech Academy")
                .startDate(LocalDateTime.now().minusYears(4))
                .endDate(LocalDateTime.now())
                .resume(resume7)
                .build();

        education10 = educationRepository.save(education10);

        Experience experience14 = Experience.builder()
                .companyName("Frontend Masters")
                .description("Developed interactive user interfaces using React and JavaScript")
                .jobTitle("Frontend Developer")
                .startDate(LocalDateTime.now().minusYears(2))
                .endDate(LocalDateTime.now())
                .resume(resume7)
                .build();

        experience14 = experienceRepository.save(experience14);

        Experience experience15 = Experience.builder()
                .companyName("Web Wizards")
                .description("Built responsive websites using HTML, CSS, and JavaScript")
                .jobTitle("Web Developer")
                .startDate(LocalDateTime.now().minusYears(3))
                .endDate(LocalDateTime.now().minusYears(1))
                .resume(resume7)
                .build();

        experience15 = experienceRepository.save(experience15);

        resume7.setEducations(new HashSet<>(Collections.singletonList(education10)));
        resume7.setExperiences(new HashSet<>(Arrays.asList(experience14, experience15)));

        Resume savedResume7 = resumeRepository.save(resume7);

// Create and save Resume 8
        Resume resume8 = Resume.builder()
                .resumeName("Resume 8")
                .skills(new HashSet<>(Arrays.asList(tensorflowSkill, pytorchSkill, dataAnalysisSkill)))
                .educations(new HashSet<>())
                .experiences(new HashSet<>())
                .build();

        resume8 = resumeRepository.save(resume8);

        Education education11 = Education.builder()
                .degree("Bachelor's")
                .major("Data Analytics")
                .schoolName("Data University")
                .startDate(LocalDateTime.now().minusYears(5))
                .endDate(LocalDateTime.now().minusYears(2))
                .resume(resume8)
                .build();

        education11 = educationRepository.save(education11);

        Education education12 = Education.builder()
                .degree("Master's")
                .major("Deep Learning")
                .schoolName("AI Academy")
                .startDate(LocalDateTime.now().minusYears(2))
                .endDate(LocalDateTime.now())
                .resume(resume8)
                .build();

        education12 = educationRepository.save(education12);

        Experience experience16 = Experience.builder()
                .companyName("AI Solutions")
                .description("Developed deep learning models using TensorFlow and PyTorch")
                .jobTitle("Deep Learning Engineer")
                .startDate(LocalDateTime.now().minusYears(3))
                .endDate(LocalDateTime.now())
                .resume(resume8)
                .build();

        experience16 = experienceRepository.save(experience16);

        Experience experience17 = Experience.builder()
                .companyName("Data Insights")
                .description("Analyzed large datasets for insights and predictions")
                .jobTitle("Data Analyst")
                .startDate(LocalDateTime.now().minusYears(4))
                .endDate(LocalDateTime.now().minusYears(1))
                .resume(resume8)
                .build();

        experience17 = experienceRepository.save(experience17);

        resume8.setEducations(new HashSet<>(Arrays.asList(education11, education12)));
        resume8.setExperiences(new HashSet<>(Arrays.asList(experience16, experience17)));

        Resume savedResume8 = resumeRepository.save(resume8);

        Role roleAdmin = Role.builder().name(ERole.ROLE_ADMIN).build();
        Role roleUser = Role.builder().name(ERole.ROLE_USER).build();
        Role roleCompany = Role.builder().name(ERole.ROLE_COMPANY).build();
        roleRepository.saveAll(new HashSet<>(Arrays.asList(roleAdmin, roleUser, roleCompany)));


        // Initialize Companies
        Company company1 = Company.builder().name("Tech Solutions Inc.")
                .createdDate(LocalDateTime.now())
                .email("techsolutions@gmail.com")
                .lastLoginDate(LocalDateTime.now())
                .password("securepassword1")
                .phoneNumber("+905361457680")
                .status(String.valueOf(Status.ACTIVE))
                .updatedDate(LocalDateTime.now())
                .companyDescription("Leading tech solutions provider specializing in software development.")
                .companyLogoUrl("url1")
                .companyName("Tech Solutions Inc.")
                .companyRegistrationNumber("1234567890")
                .companySize(500L)
                .companyWebsite("techsolutions.com")
                .sectorName("Technology")
                .build();

        Address company1Address = Address.builder()
                .street("789 Corporate Blvd")
                .city("San Francisco")
                .state("CA")
                .country("USA")
                .zipCode("94103")
                .build();
        company1.setAddressId(new HashSet<>(Collections.singletonList(company1Address)));
        addressRepository.save(company1Address);
        companyRepository.save(company1);

        Company company2 = Company.builder().name("Cloud Innovators LLC")
                .createdDate(LocalDateTime.now())
                .email("companyemail@gmail.com")
                .lastLoginDate(LocalDateTime.now())
                .password("password")
                .phoneNumber("+905361457689")
                .status(String.valueOf(Status.ACTIVE))
                .updatedDate(LocalDateTime.now())
                .companyDescription("Description")
                .companyLogoUrl("url")
                .companyName("Cloud Innovators LLC")
                .companyRegistrationNumber("1214241241")
                .companySize(1000L)
                .companyWebsite("website.com")
                .sectorName("Healthcare")
                .build();

        Address company2Address = Address.builder()
                .street("123 Tech Drive")
                .city("Seattle")
                .state("WA")
                .country("USA")
                .zipCode("98101")
                .build();
        company2.setAddressId(new HashSet<>(Collections.singletonList(company2Address)));
        addressRepository.save(company2Address);
        companyRepository.save(company2);

        Company company3 = Company.builder().name("AI Pioneers")
                .createdDate(LocalDateTime.now())
                .email("aipioneers@gmail.com")
                .lastLoginDate(LocalDateTime.now())
                .password("securepassword3")
                .phoneNumber("+905361457681")
                .status(String.valueOf(Status.ACTIVE))
                .updatedDate(LocalDateTime.now())
                .companyDescription("Innovative AI solutions for businesses worldwide.")
                .companyLogoUrl("url2")
                .companyName("AI Pioneers")
                .companyRegistrationNumber("9876543210")
                .companySize(300L)
                .companyWebsite("aipioneers.com")
                .sectorName("Artificial Intelligence")
                .build();

        Address company3Address = Address.builder()
                .street("456 AI Park")
                .city("Boston")
                .state("MA")
                .country("USA")
                .zipCode("02115")
                .build();
        company3.setAddressId(new HashSet<>(Collections.singletonList(company3Address)));
        addressRepository.save(company3Address);
        companyRepository.save(company3);

// Initialize Users

        Address user1Address = Address.builder()
                .street("789 Corporate Blvd")
                .city("San Francisco")
                .state("CA")
                .country("USA")
                .zipCode("94103")
                .build();

        addressRepository.save(user1Address);

        User user1 = User.builder()
                .username("alice123")
                .name("Alice")
                .lastName("Johnson")
                .email("alice@mail.com")
                .lastLoginDate(LocalDateTime.now())
                .password("hashedpassword1") // Replace with actual hashed password
                .roles(new HashSet<>(Arrays.asList(roleUser)))
                .status(String.valueOf(Status.ACTIVE))
                .phoneNumber("123-456-789")
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .resumes(new HashSet<>(Collections.singletonList(resume1)))
                .address(Set.of(user1Address))
                .build();


        Address user2Address = Address.builder()
                .street("123 Tech Drive")
                .city("Seattle")
                .state("WA")
                .country("USA")
                .zipCode("98101")
                .build();

        addressRepository.save(user2Address);

        User user2 = User.builder()
                .username("bob456")
                .name("Bob")
                .lastName("Smith")
                .email("bob@mail.com")
                .lastLoginDate(LocalDateTime.now().minusYears(2))
                .password("hashedpassword2") // Replace with actual hashed password
                .roles(new HashSet<>(Arrays.asList(roleUser)))
                .status(String.valueOf(Status.ACTIVE))
                .phoneNumber("987-654-321")
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .resumes(new HashSet<>(Collections.singletonList(resume2)))
                .address(Set.of(user2Address))
                .build();

        Address user3Address = Address.builder()
                .street("456 Innovation Road")
                .city("Austin")
                .state("TX")
                .country("USA")
                .zipCode("73301")
                .build();

        addressRepository.save(user3Address);

        User user3 = User.builder()
                .username("carol789")
                .name("Carol")
                .lastName("Davis")
                .email("carol@mail.com")
                .lastLoginDate(LocalDateTime.now().minusYears(4))
                .password("hashedpassword3") // Replace with actual hashed password
                .roles(new HashSet<>(Arrays.asList(roleCompany)))
                .status(String.valueOf(Status.ACTIVE))
                .phoneNumber("567-890-123")
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .resumes(new HashSet<>(Collections.singletonList(resume3)))
                .address(Set.of(user3Address))
                .build();

        Address user4Address = Address.builder()
                .street("789 Analytics Lane")
                .city("Chicago")
                .state("IL")
                .country("USA")
                .zipCode("60605")
                .build();

        addressRepository.save(user4Address);

        User user4 = User.builder()
                .username("dave321")
                .name("Dave")
                .lastName("Wilson")
                .email("dave@mail.com")
                .lastLoginDate(LocalDateTime.now().minusMonths(2))
                .password("hashedpassword4") // Replace with actual hashed password
                .roles(new HashSet<>(Arrays.asList(roleAdmin)))
                .status(String.valueOf(Status.ACTIVE))
                .phoneNumber("890-123-456")
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .resumes(new HashSet<>(Collections.singletonList(resume4)))
                .address(Set.of(user4Address))
                .build();

        userRepository.saveAll(new HashSet<>(Arrays.asList(user1, user2, user3, user4)));


        // Initialize more Companies
        Company company4 = Company.builder().name("NextGen Technologies")
                .createdDate(LocalDateTime.now())
                .email("nextgen@gmail.com")
                .lastLoginDate(LocalDateTime.now())
                .password("securepassword4")
                .phoneNumber("+905361457682")
                .status(String.valueOf(Status.ACTIVE))
                .updatedDate(LocalDateTime.now())
                .companyDescription("Cutting-edge technology services for the modern era.")
                .companyLogoUrl("url3")
                .companyName("NextGen Technologies")
                .companyRegistrationNumber("1122334455")
                .companySize(750L)
                .companyWebsite("nextgen.com")
                .sectorName("Technology")
                .build();

        Address company4Address = Address.builder()
                .street("789 Future Blvd")
                .city("Austin")
                .state("TX")
                .country("USA")
                .zipCode("73301")
                .build();
        company4.setAddressId(new HashSet<>(Collections.singletonList(company4Address)));
        addressRepository.save(company4Address);
        companyRepository.save(company4);

        Company company5 = Company.builder().name("Data Driven Solutions")
                .createdDate(LocalDateTime.now())
                .email("datadriven@gmail.com")
                .lastLoginDate(LocalDateTime.now())
                .password("securepassword5")
                .phoneNumber("+905361457683")
                .status(String.valueOf(Status.ACTIVE))
                .updatedDate(LocalDateTime.now())
                .companyDescription("Empowering businesses with data-driven insights.")
                .companyLogoUrl("url4")
                .companyName("Data Driven Solutions")
                .companyRegistrationNumber("6677889900")
                .companySize(200L)
                .companyWebsite("datadriven.com")
                .sectorName("Analytics")
                .build();

        Address company5Address = Address.builder()
                .street("456 Analytics Way")
                .city("Chicago")
                .state("IL")
                .country("USA")
                .zipCode("60605")
                .build();
        company5.setAddressId(new HashSet<>(Collections.singletonList(company5Address)));
        addressRepository.save(company5Address);
        companyRepository.save(company5);

        Company company6 = Company.builder().name("Innovate AI Labs")
                .createdDate(LocalDateTime.now())
                .email("innovateailabs@gmail.com")
                .lastLoginDate(LocalDateTime.now())
                .password("securepassword6")
                .phoneNumber("+905361457684")
                .status(String.valueOf(Status.ACTIVE))
                .updatedDate(LocalDateTime.now())
                .companyDescription("Pioneering the future of AI technology.")
                .companyLogoUrl("url5")
                .companyName("Innovate AI Labs")
                .companyRegistrationNumber("5544332211")
                .companySize(600L)
                .companyWebsite("innovateai.com")
                .sectorName("Artificial Intelligence")
                .build();

        Address company6Address = Address.builder()
                .street("123 AI Boulevard")
                .city("New York")
                .state("NY")
                .country("USA")
                .zipCode("10001")
                .build();
        company6.setAddressId(new HashSet<>(Collections.singletonList(company6Address)));
        addressRepository.save(company6Address);
        companyRepository.save(company6);

// Initialize more Users

        Address user5Address = Address.builder()
                .street("123 AI Boulevard")
                .city("Boston")
                .state("MA")
                .country("USA")
                .zipCode("02115")
                .build();

        addressRepository.save(user5Address);

        User user5 = User.builder()
                .username("eve101")
                .name("Eve")
                .lastName("Adams")
                .email("eve@mail.com")
                .password("hashedpassword5") // Replace with actual hashed password
                .lastLoginDate(LocalDateTime.now().minusYears(2))
                .roles(new HashSet<>(Arrays.asList(roleUser)))
                .status(String.valueOf(Status.ACTIVE))
                .phoneNumber("111-222-333")
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .resumes(new HashSet<>(Collections.singletonList(resume5)))
                .address(Set.of(user5Address))
                .build();

        Address user6Address = Address.builder()
                .street("789 Data Park")
                .city("New York")
                .state("NY")
                .country("USA")
                .zipCode("10001")
                .build();

        addressRepository.save(user6Address);

        User user6 = User.builder()
                .username("frank202")
                .name("Frank")
                .lastName("Baker")
                .email("frank@mail.com")
                .password("hashedpassword6") // Replace with actual hashed password
                .lastLoginDate(LocalDateTime.now().minusYears(2))
                .roles(new HashSet<>(Arrays.asList(roleCompany)))
                .status(String.valueOf(Status.DELETED))
                .phoneNumber("444-555-666")
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .resumes(new HashSet<>(Collections.singletonList(resume6)))
                .address(Set.of(user6Address))
                .build();

        Address user7Address = Address.builder()
                .street("456 DevOps Ave")
                .city("Portland")
                .state("OR")
                .country("USA")
                .zipCode("97201")
                .build();

        addressRepository.save(user7Address);

        User user7 = User.builder()
                .username("grace303")
                .name("Grace")
                .lastName("Carter")
                .email("grace@mail.com")
                .password("hashedpassword7") // Replace with actual hashed password
                .lastLoginDate(LocalDateTime.now().minusYears(1))
                .roles(new HashSet<>(Arrays.asList(roleAdmin)))
                .status(String.valueOf(Status.ACTIVE))
                .phoneNumber("777-888-999")
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .resumes(new HashSet<>(Collections.singletonList(resume7)))
                .address(Set.of(user7Address))
                .build();


        Address user8Address = Address.builder()
                .street("789 ML Drive")
                .city("Los Angeles")
                .state("CA")
                .country("USA")
                .zipCode("90001")
                .build();

        addressRepository.save(user8Address);

        User user8 = User.builder()
                .username("henry404")
                .name("Henry")
                .lastName("Daniels")
                .email("henry@mail.com")
                .password("hashedpassword8") // Replace with actual hashed password
                .lastLoginDate(LocalDateTime.now().minusYears(3))
                .roles(new HashSet<>(Arrays.asList(roleUser)))
                .status(String.valueOf(Status.DELETED))
                .phoneNumber("333-444-555")
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .resumes(new HashSet<>(Collections.singletonList(resume8)))
                .address(Set.of(user8Address))
                .build();

        Address user9Address = Address.builder()
                .street("123 Blockchain Way")
                .city("Miami")
                .state("FL")
                .country("USA")
                .zipCode("33101")
                .build();

        addressRepository.save(user9Address);


        User user9 = User.builder()
                .username("ivy505")
                .name("Ivy")
                .lastName("Evans")
                .email("ivy@mail.com")
                .password("hashedpassword9") // Replace with actual hashed password
                .lastLoginDate(LocalDateTime.now().minusYears(5))
                .roles(new HashSet<>(Arrays.asList(roleUser)))
                .status(String.valueOf(Status.ACTIVE))
                .phoneNumber("222-333-444")
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .resumes(new HashSet<>(Collections.singletonList(resume1)))
                .address(Set.of(user9Address))
                .build();


        Address user10Address = Address.builder()
                .street("456 Cloud Street")
                .city("Denver")
                .state("CO")
                .country("USA")
                .zipCode("80201")
                .build();

        addressRepository.save(user10Address);

        User user10 = User.builder()
                .username("jack606")
                .name("Jack")
                .lastName("Foster")
                .email("jack@mail.com")
                .password("hashedpassword10") // Replace with actual hashed password
                .lastLoginDate(LocalDateTime.now().minusYears(1))
                .roles(new HashSet<>(Arrays.asList(roleCompany)))
                .status(String.valueOf(Status.ACTIVE))
                .phoneNumber("555-666-777")
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .resumes(new HashSet<>(Collections.singletonList(resume2)))
                .build();

        userRepository.saveAll(new HashSet<>(Arrays.asList(user5, user6, user7, user8, user9, user10)));



        // Initialize Job Postings
        JobPosting job1 = JobPosting.builder()
                .jobTitle("Backend Engineer")
                .jobDescription("Develop APIs and microservices.")
                .jobLocation("Remote")
                .company(company1)
                .tag(new HashSet<>(Arrays.asList(backendTag, urgentTag, fullTimeTag)))
                .applicant(new HashSet<>(Arrays.asList(user1, user2)))
                .deadline(LocalDateTime.now().plusMonths(1))
                .jobPostingStatus(JobPostingStatus.ACTIVE)
                .sector("Technology")
                .build();

        JobPosting job2 = JobPosting.builder()
                .jobTitle("Data Scientist")
                .jobDescription("Analyze data and build ML models.")
                .jobLocation("New York")
                .company(company1)
                .tag(new HashSet<>(Arrays.asList(remoteTag, aiTag, mlTag)))
                .applicant(new HashSet<>(Arrays.asList(user2, user5)))
                .deadline(LocalDateTime.now().plusMonths(2))
                .jobPostingStatus(JobPostingStatus.ACTIVE)
                .sector("Artificial Intelligence")
                .build();

        JobPosting job3 = JobPosting.builder()
                .jobTitle("DevOps Engineer")
                .jobDescription("Maintain CI/CD pipelines and manage Kubernetes clusters.")
                .jobLocation("Seattle")
                .company(company2)
                .tag(new HashSet<>(Arrays.asList(devOpsTag, cloudTag, fullTimeTag)))
                .applicant(new HashSet<>(Arrays.asList(user3, user4)))
                .deadline(LocalDateTime.now().minusDays(10))
                .jobPostingStatus(JobPostingStatus.EXPIRED)
                .sector("DevOps")
                .build();

        JobPosting job4 = JobPosting.builder()
                .jobTitle("Frontend Developer")
                .jobDescription("Design and implement user-friendly interfaces.")
                .jobLocation("Austin")
                .company(company3)
                .tag(new HashSet<>(Arrays.asList(frontendTag, remoteTag, fullTimeTag)))
                .applicant(new HashSet<>(Arrays.asList(user1, user6)))
                .deadline(LocalDateTime.now().plusDays(15))
                .jobPostingStatus(JobPostingStatus.ACTIVE)
                .sector("Frontend Development")
                .build();

        JobPosting job5 = JobPosting.builder()
                .jobTitle("Cloud Engineer")
                .jobDescription("Design and maintain scalable cloud architectures.")
                .jobLocation("San Francisco")
                .company(company4)
                .tag(new HashSet<>(Arrays.asList(cloudTag, urgentTag, fullTimeTag)))
                .applicant(new HashSet<>(Arrays.asList(user2, user5)))
                .deadline(LocalDateTime.now().minusDays(15))
                .sector("Cloud Computing")
                .build();

        JobPosting job6 = JobPosting.builder()
                .jobTitle("Machine Learning Engineer")
                .jobDescription("Build and optimize machine learning pipelines.")
                .jobLocation("Boston")
                .company(company5)
                .tag(new HashSet<>(Arrays.asList(mlTag, aiTag, urgentTag)))
                .applicant(new HashSet<>(Arrays.asList(user5, user8)))
                .deadline(LocalDateTime.now().minusDays(5))
                .jobPostingStatus(JobPostingStatus.EXPIRED)
                .sector("Machine Learning")
                .build();

        JobPosting job7 = JobPosting.builder()
                .jobTitle("Software Engineer")
                .jobDescription("Develop robust software systems for enterprise clients.")
                .jobLocation("Remote")
                .company(company6)
                .tag(new HashSet<>(Arrays.asList(fullStackTag, backendTag, remoteTag)))
                .applicant(new HashSet<>(Arrays.asList(user1, user9)))
                .deadline(LocalDateTime.now().plusMonths(1))
                .jobPostingStatus(JobPostingStatus.ACTIVE)
                .sector("Software Engineer")
                .build();

        JobPosting job8 = JobPosting.builder()
                .jobTitle("Cybersecurity Analyst")
                .jobDescription("Ensure the security of company systems and data.")
                .jobLocation("Chicago")
                .company(company4)
                .tag(new HashSet<>(Arrays.asList(cybersecurityTag, fullTimeTag)))
                .applicant(new HashSet<>(Arrays.asList(user3, user10)))
                .deadline(LocalDateTime.now().plusMonths(2))
                .jobPostingStatus(JobPostingStatus.CANCELLED)
                .sector("Cybersecurity")
                .build();

        JobPosting job9 = JobPosting.builder()
                .jobTitle("Blockchain Developer")
                .jobDescription("Develop blockchain solutions and smart contracts.")
                .jobLocation("New York")
                .company(company6)
                .tag(new HashSet<>(Arrays.asList(blockchainTag, fintechTag)))
                .applicant(new HashSet<>(Arrays.asList(user4, user7)))
                .deadline(LocalDateTime.now().plusDays(20))
                .jobPostingStatus(JobPostingStatus.ACTIVE)
                .sector("Blockchain")
                .build();

        jobPostingRepository.saveAll(new HashSet<>(Arrays.asList(
                job1, job2, job3, job4, job5, job6, job7, job8, job9
        )));



        // Additional Job Postings
        JobPosting job10 = JobPosting.builder()
                .jobTitle("AI Research Scientist")
                .jobDescription("Conduct cutting-edge AI research and publish findings.")
                .jobLocation("Boston")
                .company(company5)
                .tag(new HashSet<>(Arrays.asList(aiTag, mlTag, fullTimeTag)))
                .applicant(new HashSet<>(Arrays.asList(user5, user6)))
                .deadline(LocalDateTime.now().minusDays(2))
                .jobPostingStatus(JobPostingStatus.EXPIRED)
                .sector("AI Research")
                .build();

        JobPosting job11 = JobPosting.builder()
                .jobTitle("Full Stack Developer")
                .jobDescription("Build scalable full-stack applications.")
                .jobLocation("Remote")
                .company(company4)
                .tag(new HashSet<>(Arrays.asList(fullStackTag, backendTag, frontendTag, remoteTag)))
                .applicant(new HashSet<>(Arrays.asList(user1, user2, user7)))
                .deadline(LocalDateTime.now().plusMonths(3))
                .jobPostingStatus(JobPostingStatus.ACTIVE)
                .sector("Full Stack Development")
                .build();

        JobPosting job12 = JobPosting.builder()
                .jobTitle("Data Engineer")
                .jobDescription("Design data pipelines and maintain data infrastructure.")
                .jobLocation("Seattle")
                .company(company2)
                .tag(new HashSet<>(Arrays.asList(dataScienceTag, cloudTag, urgentTag)))
                .applicant(new HashSet<>(Arrays.asList(user3, user8)))
                .deadline(LocalDateTime.now().plusMonths(1))
                .jobPostingStatus(JobPostingStatus.CANCELLED)
                .deadline(LocalDateTime.now().plusMonths(2))
                .sector("Data Engineering")
                .build();

        JobPosting job13 = JobPosting.builder()
                .jobTitle("Mobile App Developer")
                .jobDescription("Develop mobile applications for iOS and Android.")
                .jobLocation("Austin")
                .company(company3)
                .tag(new HashSet<>(Arrays.asList(remoteTag, urgentTag)))
                .applicant(new HashSet<>(Arrays.asList(user4, user10)))
                .deadline(LocalDateTime.now().plusDays(30))
                .jobPostingStatus(JobPostingStatus.ACTIVE)
                .sector("Mobile App Development")
                .build();

        JobPosting job14 = JobPosting.builder()
                .jobTitle("Big Data Analyst")
                .jobDescription("Analyze and interpret large datasets to gain insights.")
                .jobLocation("San Francisco")
                .company(company4)
                .tag(new HashSet<>(Arrays.asList(fullTimeTag)))
                .applicant(new HashSet<>(Arrays.asList(user5, user6)))
                .deadline(LocalDateTime.now().plusMonths(1))
                .jobPostingStatus(JobPostingStatus.ACTIVE)
                .sector("Big Data Analytics")
                .build();

        JobPosting job15 = JobPosting.builder()
                .jobTitle("React Native Developer")
                .jobDescription("Create cross-platform mobile applications.")
                .jobLocation("New York")
                .company(company6)
                .tag(new HashSet<>(Arrays.asList(remoteTag)))
                .applicant(new HashSet<>(Arrays.asList(user1, user9)))
                .deadline(LocalDateTime.now().minusDays(15))
                .jobPostingStatus(JobPostingStatus.EXPIRED)
                .sector("React Development")
                .build();

        JobPosting job16 = JobPosting.builder()
                .jobTitle("Systems Architect")
                .jobDescription("Design high-level architectures for complex systems.")
                .jobLocation("Chicago")
                .company(company4)
                .tag(new HashSet<>(Arrays.asList(urgentTag, cloudTag, backendTag)))
                .applicant(new HashSet<>(Arrays.asList(user2, user8)))
                .deadline(LocalDateTime.now().plusMonths(2))
                .jobPostingStatus(JobPostingStatus.ACTIVE)
                .sector("Systems Architecture")
                .build();

        JobPosting job17 = JobPosting.builder()
                .jobTitle("Game Developer")
                .jobDescription("Develop engaging games for multiple platforms.")
                .jobLocation("Remote")
                .company(company5)
                .tag(new HashSet<>(Arrays.asList(remoteTag, aiTag, blockchainTag)))
                .applicant(new HashSet<>(Arrays.asList(user3, user4)))
                .sector("Game Development")
                .build();

        JobPosting job18 = JobPosting.builder()
                .jobTitle("Marketing Specialist")
                .jobDescription("Develop marketing strategies and campaigns.")
                .jobLocation("Austin")
                .company(company3)
                .tag(new HashSet<>(Arrays.asList(urgentTag, remoteTag)))
                .applicant(new HashSet<>(Arrays.asList(user5, user7)))
                .deadline(LocalDateTime.now().plusDays(10))
                .jobPostingStatus(JobPostingStatus.CANCELLED)
                .sector("Marketing")
                .build();

        JobPosting job19 = JobPosting.builder()
                .jobTitle("Cloud Security Engineer")
                .jobDescription("Enhance and secure cloud systems.")
                .jobLocation("Seattle")
                .company(company2)
                .tag(new HashSet<>(Arrays.asList(cloudTag, cybersecurityTag, fullTimeTag)))
                .applicant(new HashSet<>(Arrays.asList(user6, user10)))
                .deadline(LocalDateTime.now().plusMonths(1))
                .jobPostingStatus(JobPostingStatus.ACTIVE)
                .sector("Cloud Security")
                .build();

        JobPosting job20 = JobPosting.builder()
                .jobTitle("Natural Language Processing Engineer")
                .jobDescription("Develop NLP models and systems.")
                .jobLocation("New York")
                .company(company6)
                .tag(new HashSet<>(Arrays.asList(mlTag, fullTimeTag, cloudTag)))
                .applicant(new HashSet<>(Arrays.asList(user8, user9)))
                .deadline(LocalDateTime.now().plusDays(25))
                .jobPostingStatus(JobPostingStatus.ACTIVE)
                .sector("Natural Language Processing")
                .build();

        jobPostingRepository.saveAll(new HashSet<>(Arrays.asList(
                job10, job11, job12, job13, job14, job15, job16, job17, job18, job19, job20
        )));
    }
}