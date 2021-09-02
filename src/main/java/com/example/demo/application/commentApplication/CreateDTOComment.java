package com.example.demo.application.commentApplication;

import java.util.UUID;
import javax.validation.constraints.*;
import org.springframework.validation.annotation.Validated;
import lombok.*;

@Validated
public @Getter @Setter @NoArgsConstructor class CreateDTOComment {
    @NotBlank
    @Size(max = 2000)
    public String text;
    @Min(0)
    @Max(5)
    public int score;
    public UUID user;
    public UUID pizza;
}
