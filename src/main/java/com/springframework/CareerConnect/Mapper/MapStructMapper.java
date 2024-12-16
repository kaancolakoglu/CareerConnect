package com.springframework.CareerConnect.Mapper;

import com.springframework.CareerConnect.domain.*;
import com.springframework.CareerConnect.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;


@Mapper(componentModel = "spring")
public interface MapStructMapper {

    @Mapping(target = "tags", expression = "java(mapTagsToTagNames(jobPosting.getTag()))")
    @Mapping(target = "applicantNames", expression = "java(mapApplicantsToApplicantNames(jobPosting.getApplicant()))")
    JobPostingDTO mapToJobPostingDTO(JobPosting jobPosting);
    JobPosting mapToJobPosting(JobPostingDTO jobPostingDTO);

    Tag mapToTag(TagDTO tagDTO);
    TagDTO mapToTagDTO(Tag tag);

    CompanyDTO mapToCompanyDTO(Company company);
    Company mapToCompany(CompanyDTO companyDTO);


    CompanyProfileDetailsDTO mapToCompanyProfileDetailsDTO(Company company);
    Company mapToCompany(CompanyProfileDetailsDTO companyProfileDetailsDTO);

    UserDTO mapToUserDTO(User user);
    User mapToUser(UserDTO userDTO);

    UpdateCompanyDetailsDTO mapToUpdateCompanyDetailsDTO(Company company);
    Company mapToCompany(UpdateCompanyDetailsDTO updateCompanyDetailsDTO);

    default List<String> mapTagsToTagNames(Set<Tag> tags) {
        if (tags == null) {
            return List.of();
        }
        return tags.stream()
                .map(Tag::getTagName)
                .toList();
    }

    default List<String> mapApplicantsToApplicantNames(Set<User> applicants) {
        if (applicants == null) {
            return List.of();
        }
        return applicants.stream()
                .map(User::getName)
                .toList();
    }


    ApplicationDTO mapToApplicationDTO(Application application);
    Application mapToApplication(ApplicationDTO applicationDTO);



}
