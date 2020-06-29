package com.tests.task_tracker.Dto;

import lombok.Data;

@Data
public class UserRegistrationDto {
    private String first_name;
    private String last_name;
    private String email;
    private String password;
}
