package com.springframework.CareerConnect.services;

import com.springframework.CareerConnect.domain.Role;
import com.springframework.CareerConnect.domain.User;
import com.springframework.CareerConnect.model.UserDTO;

public interface UserService{

    Iterable<User> findAllUsers();

    User registerNewUserAccount(UserDTO userDTO);

    User addRoleToUser(User user, Role role);
}
