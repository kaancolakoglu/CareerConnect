package com.springframework.CareerConnect.services;

import com.springframework.CareerConnect.domain.User;
import com.springframework.CareerConnect.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Iterable<User> findAllUsers() {
        return userRepository.findAll();
    }
}
