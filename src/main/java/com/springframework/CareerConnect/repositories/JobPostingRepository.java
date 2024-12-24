package com.springframework.CareerConnect.repositories;

import com.springframework.CareerConnect.domain.JobPosting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobPostingRepository extends JpaRepository<JobPosting, String> {
    Optional<JobPosting> findJobPostingByJobId(Long jobId);

    @Query(value = "SELECT jp.* FROM job_posting jp " +
            "INNER JOIN dbo.job_posting_tags jpt ON jp.job_id = jpt.job_id " +
            "INNER JOIN dbo.tag t ON t.tag_id = jpt.tag_id " +
            "INNER JOIN dbo.company c ON c.profile_id = jp.company_id " +
            "INNER JOIN dbo.company_address ca ON c.profile_id = ca.company_id " +
            "INNER JOIN dbo.address a ON a.address_id = ca.address_id " +
            "WHERE (:jobTitle IS NULL OR jp.job_title LIKE %:jobTitle%) " +
            "AND (:jobLocation IS NULL OR jp.job_location LIKE %:jobLocation%) " +
            "AND (:companyName IS NULL OR c.company_name LIKE %:companyName%) " +
            "AND (:tagName IS NULL OR t.tag_name LIKE %:tagName%) " +
            "AND (:sectorName IS NULL OR c.sector_name LIKE %:sectorName%)" +
            "AND (:companyId IS NULL OR c.profile_id = :companyId)",
            nativeQuery = true)
    List<JobPosting> findJobPostingsByCriteria(@Param("jobTitle") String jobTitle,
                                               @Param("jobLocation") String jobLocation,
                                               @Param("companyName") String companyName,
                                               @Param("tagName") String tagName,
                                               @Param("sectorName") String sectorName,
                                               @Param("companyId") Long companyId
    );
}
