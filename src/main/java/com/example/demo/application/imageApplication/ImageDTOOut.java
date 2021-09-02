package com.example.demo.application.imageApplication;

import lombok.*;

public @Getter @Setter @NoArgsConstructor class ImageDTOOut extends ImageDTO {
    private byte[] data;
}
