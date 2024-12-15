package com.springframework.CareerConnect.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserProfileDetailsDTO {


    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;



}
