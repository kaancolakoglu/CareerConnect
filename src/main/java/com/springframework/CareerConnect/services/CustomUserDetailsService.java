package com.springframework.CareerConnect.services;

import com.springframework.CareerConnect.domain.Role;
import com.springframework.CareerConnect.domain.User;
import com.springframework.CareerConnect.repositories.UserRepository;
import com.springframework.CareerConnect.security.CustomUserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException("No user found with username: " + email);
        }

        return new CustomUserDetails(user);
    }

    private static List<GrantedAuthority> getAuthorities(Set<Role> roles) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Role role: roles) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return grantedAuthorities;
    }
}


