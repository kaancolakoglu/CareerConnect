package com.springframework.CareerConnect.controllers;

import com.springframework.CareerConnect.Mapper.MapStructMapper;
import com.springframework.CareerConnect.domain.User;
import com.springframework.CareerConnect.model.UserDTO;
import com.springframework.CareerConnect.repositories.UserRepository;
import com.springframework.CareerConnect.services.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserServiceImpl userServiceImpl;
    private final UserRepository userRepository;
    private final MapStructMapper mapStructMapper;

    public UserController(UserServiceImpl userServiceImpl, UserRepository userRepository, MapStructMapper mapStructMapper) {
        this.userServiceImpl = userServiceImpl;
        this.userRepository = userRepository;
        this.mapStructMapper = mapStructMapper;
    }
    @GetMapping("/{userID}")
    public ResponseEntity<UserDTO> getUserDetails(@PathVariable Long userID) {
        User user = userServiceImpl.findUserById(userID);

        UserDTO userDTO = mapStructMapper.mapToUserDTO(user);

        return ResponseEntity.ok(userDTO);
    }
}
