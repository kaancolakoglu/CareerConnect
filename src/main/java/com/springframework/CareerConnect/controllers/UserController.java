package com.springframework.CareerConnect.controllers;

import com.springframework.CareerConnect.domain.User;
import com.springframework.CareerConnect.repositories.UserRepository;
import com.springframework.CareerConnect.services.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    public UserController(UserServiceImpl userServiceImpl, UserRepository userRepository) {
        this.userServiceImpl = userServiceImpl;
        this.userRepository = userRepository;
    }

    private final UserServiceImpl userServiceImpl;
    private final UserRepository userRepository;


    @GetMapping("/{userID}")
    public ResponseEntity<User> getUserDetails(@PathVariable Long jobId) {
        User user = userServiceImpl.findUserById();

        return ResponseEntity.ok(new User());
    }
}
