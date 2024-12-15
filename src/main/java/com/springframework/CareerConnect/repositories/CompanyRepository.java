package com.springframework.CareerConnect.repositories;

import com.springframework.CareerConnect.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    @Query("SELECT c FROM Company c WHERE " +
            "(:name IS NULL OR c.companyName LIKE %:name%) AND " +
            "(:sector IS NULL OR c.sectorName = :sector) AND " +
            "(:minSize IS NULL OR c.companySize >= :minSize) AND " +
            "(:maxSize IS NULL OR c.companySize <= :maxSize)")
    List<Company> findCompaniesByCriteria(@Param("name") String name,
                                          @Param("sector") String sector,
                                          @Param("minSize") Long minSize,
                                          @Param("maxSize") Long maxSize);
}
