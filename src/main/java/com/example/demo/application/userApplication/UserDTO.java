package com.example.demo.application.userApplication;

import java.util.UUID;
import com.example.demo.domain.userDomain.Role;
import lombok.*;

public @NoArgsConstructor @Getter @Setter class UserDTO {
    private UUID id;
    private String name;
    private String surname;
    private String email;
    private Role role;
}
