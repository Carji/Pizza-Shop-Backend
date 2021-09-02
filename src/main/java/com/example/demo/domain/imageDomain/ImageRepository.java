package com.example.demo.domain.imageDomain;

import java.util.UUID;

import com.example.demo.application.imageApplication.ImageDTO;
import com.example.demo.core.ExistsByField;
import com.example.demo.core.FindById;

public interface ImageRepository extends FindById<Image, UUID>, ExistsByField{
    ImageDTO save(Image image);
}
