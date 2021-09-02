package com.example.demo.application.userApplication;

import javax.validation.constraints.*;
import com.example.demo.domain.userDomain.Role;
import org.springframework.validation.annotation.Validated;
import lombok.*;

@Validated
public @NoArgsConstructor @Getter @Setter class CreateUserDTO {
    @NotBlank(message = "Name error.")
    private String name;
    @NotBlank(message = "Surname error.")
    private String surname;
    @Email(message = "That's not a valid email.")
    private String email;
    @NotNull(message = "Password can't be null.")
    private String password;
    @NotNull(message = "Incorrect role.")
    private Role role = Role.USER;
}