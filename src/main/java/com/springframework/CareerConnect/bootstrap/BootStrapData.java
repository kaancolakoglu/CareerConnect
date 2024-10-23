package com.springframework.CareerConnect.bootstrap;

import com.springframework.CareerConnect.domain.JobPosting;
import com.springframework.CareerConnect.domain.User;
import com.springframework.CareerConnect.repositories.JobPostingRepository;
import com.springframework.CareerConnect.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final UserRepository userRepository;
    private final JobPostingRepository jobPostingRepository;

    public BootStrapData(UserRepository userRepository, JobPostingRepository jobPostingRepository) {
        this.userRepository = userRepository;
        this.jobPostingRepository = jobPostingRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        User john = User.builder()
                .name("John Doe")
                .email("johndoe@gmail.com")
                .build();

        JobPosting job1 = JobPosting.builder()
                .jobTitle("Software Engineer")
                .jobDescription("Developing software")
                .jobLocation("Remote")
                .jobType("Full-time")
                .jobCategory("Software")
                .build();

        User johnSaved = userRepository.save(john);
        JobPosting job1Saved = jobPostingRepository.save(job1);
        johnSaved.getSavedJobPosting().add(job1Saved);
        userRepository.save(johnSaved);


        User jane = User.builder()
                .name("Jane Doe")
                .email("janedoe@gmail.com")
                .build();

        JobPosting job2 = JobPosting
                .builder()
                .jobTitle("Data Analyst")
                .jobDescription("Analyzing data")
                .jobLocation("Remote")
                .jobType("Full-time")
                .jobCategory("Data")
                .build();

        User janeSaved = userRepository.save(jane);
        JobPosting job2Saved = jobPostingRepository.save(job2);
        janeSaved.getSavedJobPosting().add(job2Saved);
        userRepository.save(janeSaved);

        System.out.println("Users: " + userRepository.count());
        System.out.println("Job Postings: " + jobPostingRepository.count());


    }
}