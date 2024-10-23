package com.springframework.CareerConnect.services;

import com.springframework.CareerConnect.domain.User;

public interface UserService{

    Iterable<User> findAllUsers();
}
