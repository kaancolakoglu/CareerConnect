package com.springframework.CareerConnect.Mapper;

import com.springframework.CareerConnect.domain.*;
import com.springframework.CareerConnect.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public interface MapStructMapper {

    //@Mapping(target = "tag", expression = "java(mapTagsToTagNames(jobPosting.getTag()))")
    //@Mapping(target = "applicantNames", expression = "java(mapApplicantsToApplicantNames(jobPosting.getApplicant()))")
    //JobPostingDTO mapToJobPostingDTO(JobPosting jobPosting);


    //JobPosting mapToJobPosting(JobPostingRequest jobPostingRequest);

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

    default Set<TagDTO> mapTagsToTagNames(Set<Tag> tags) {
        if (tags == null) {
            return new HashSet<>();
        }

        return tags.stream()
                .map(tag -> new TagDTO(tag.getTagId(), tag.getTagName())) // Assuming TagDTO has an ID and name
                .collect(Collectors.toSet());
    }


    default List<String> mapApplicantsToApplicantNames(Set<User> applicants) {
        if (applicants == null) {
            return List.of();
        }
        return applicants.stream()
                .map(User::getName)
                .toList();
    }



    @Mapping(source = "jobPosting.jobId", target = "jobId")
    @Mapping(source = "user.profileId", target = "userId")
    ApplicationDTO mapToApplicationDTO(Application application);

    @Mapping(source = "jobId", target = "jobPosting.jobId")
    @Mapping(source = "userId", target = "user.profileId")
    Application mapToApplication(ApplicationDTO applicationDTO);


    @Mapping(target = "userName", source = "user.name")
    @Mapping(target = "userLastName", source = "user.lastName")
    ResumeDTO toResumeDTO(Resume resume);

    @Mapping(target = "user", ignore = true)
    Resume toResumeEntity(ResumeDTO resumeDTO);


    List<EducationDTO> toEducationDTOList(Set<Education> educations);
    List<ExperienceDTO> toExperienceDTOList(Set<Experience> experiences);
    List<SkillDTO> toSkillDTOList(Set<Skill> skills);
}
