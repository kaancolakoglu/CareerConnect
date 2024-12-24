package com.springframework.CareerConnect.Mapper;

import com.springframework.CareerConnect.domain.Company;
import com.springframework.CareerConnect.domain.JobPosting;
import com.springframework.CareerConnect.domain.Tag;
import com.springframework.CareerConnect.domain.User;
import com.springframework.CareerConnect.model.CompanyDTO;
import com.springframework.CareerConnect.model.JobPostingDTO;
import com.springframework.CareerConnect.model.TagDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class JobPostingMapper {

    // Map JobPosting to JobPostingDTO
    public static JobPostingDTO toJobPostingDTO(JobPosting jobPosting) {
        if (jobPosting == null) {
            return null;
        }

        JobPostingDTO jobPostingDTO = new JobPostingDTO();
        jobPostingDTO.setJobId(jobPosting.getJobId());
        jobPostingDTO.setJobTitle(jobPosting.getJobTitle());
        jobPostingDTO.setJobDescription(jobPosting.getJobDescription());
        jobPostingDTO.setJobLocation(jobPosting.getJobLocation());
        jobPostingDTO.setTag(mapTagsToTagDTOs(jobPosting.getTag()));
        jobPostingDTO.setApplicantNames(mapApplicantsToNames(jobPosting.getApplicant()));
        jobPostingDTO.setCompany(toCompanyDTO(jobPosting.getCompany()));

        return jobPostingDTO;
    }

    // Map Company to CompanyDTO
    public static CompanyDTO toCompanyDTO(Company company) {
        if (company == null) {
            return null;
        }

        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setCompanyId(company.getProfileId());
        companyDTO.setName(company.getCompanyName());
        companyDTO.setJobPostings(null);

        return companyDTO;
    }

    // Map Set<Tag> to Set<TagDTO>
    public static Set<TagDTO> mapTagsToTagDTOs(Set<Tag> tags) {
        if (tags == null || tags.isEmpty()) {
            return Set.of();
        }

        return tags.stream()
                .map(tag -> new TagDTO(tag.getTagId(), tag.getTagName()))
                .collect(Collectors.toSet());
    }

    public static List<String> mapApplicantsToNames(Set<User> applicants) {
        if (applicants == null || applicants.isEmpty()) {
            return List.of();
        }

        return applicants.stream()
                .map(User::getName)
                .collect(Collectors.toList());
    }
}
