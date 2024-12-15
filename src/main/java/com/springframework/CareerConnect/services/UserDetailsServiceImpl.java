package com.springframework.CareerConnect.services;

import com.springframework.CareerConnect.domain.Company;
import com.springframework.CareerConnect.domain.User;
import com.springframework.CareerConnect.repositories.CompanyRepository;
import com.springframework.CareerConnect.repositories.UserRepository;
import com.springframework.CareerConnect.security.UserDetailsImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    public UserDetailsServiceImpl(UserRepository userRepository, CompanyRepository companyRepository) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Attempting to find user or company with username: {}", username);

        User user = userRepository.findByUsername(username)
                .orElse(null);
        Company company = companyRepository.findByUsername(username)
                .orElse(null);

        if (user != null) {
            log.info("User found: {}", username);
            return UserDetailsImpl.build(user);
        } else if (company != null) {
            log.info("Company found: {}", username);
            return UserDetailsImpl.build(company);
        } else {
            log.error("No user or company found with username: {}", username);
            throw new UsernameNotFoundException("User or Company not found with username: " + username);
        }
    }
}


