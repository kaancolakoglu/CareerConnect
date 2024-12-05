package com.springframework.CareerConnect.repositories;

import com.springframework.CareerConnect.domain.Role;
import com.springframework.CareerConnect.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole name);
}
