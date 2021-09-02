package com.example.demo.application.imageApplication;

import javax.validation.constraints.NotBlank;

import lombok.*;

public @Getter @Setter @NoArgsConstructor class CreateOrUpdateImageDTO {
    @NotBlank
    public byte[] data;
}
