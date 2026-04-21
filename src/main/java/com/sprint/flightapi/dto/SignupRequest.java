package com.sprint.flightapi.dto;

import lombok.Data;

@Data
public class SignupRequest {
    private String firstName;
    private String lastName;
    private String username; // separate username
    private String email;    // separate email
    private String password;
    private String confirmPassword;
    private String phoneNumber;
    private Long cityId;
    private String country;
}
