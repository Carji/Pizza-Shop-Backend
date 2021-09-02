package com.example.demo.application.imageApplication;

import java.io.IOException;
import java.util.UUID;

public interface ImageApplication{
    public ImageDTO save(CreateOrUpdateImageDTO dto);
    public ImageDTOOut get(UUID id) throws IOException;
}
