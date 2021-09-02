package com.example.demo.application.userApplication;

import javax.validation.constraints.Email;

public class loginDTO {
    @Email
    private String email;
    private String password;
}
