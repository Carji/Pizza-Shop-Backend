package com.example.demo.infrastructure.imageInfrastructure;

import java.time.Duration;
import java.util.*;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.demo.application.imageApplication.ImageDTO;
import com.example.demo.core.Exceptions.*;
import com.example.demo.domain.imageDomain.*;
import org.modelmapper.ModelMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ImageRepositoryImp implements ImageRepository {

    Image image;
    private final RedisTemplate<String, byte[]> template;
    private final ModelMapper modelMapper;

    public ImageRepositoryImp(final RedisTemplate<String, byte[]> template, final ModelMapper modelMapper) {
        this.template = template;
        this.modelMapper = modelMapper;
    }

    @Override
    public ImageDTO save(Image image) {
        try {
            this.template.opsForValue().set(image.getId().toString(), image.getData(), Duration.ofDays(2));
            Cloudinary cloudinary = new Cloudinary();
            Map result = cloudinary.uploader().upload(image.getData(),
                    ObjectUtils.asMap("public_id", image.getId().toString()));
        } catch (Exception e) {
            throw new InternalServerErrorException(InternalServerErrorEnum.REDIRECT);
        } finally {
            if (!this.template.getConnectionFactory().getConnection().isClosed()) {
                this.template.getConnectionFactory().getConnection().close();
            }
        }
        return this.modelMapper.map(image, ImageDTO.class);
    }

    @Override
    public Optional<Image> findById(UUID id) {
        try {
            byte[] bytes = this.template.opsForValue().get(id.toString());
            if (bytes == null) {
                return Optional.of(null);
            }
            Image image = new Image();
            image.setId(id);
            image.setData(bytes);
            return Optional.of(image);
        } catch (Exception e) {
            throw new InternalServerErrorException(InternalServerErrorEnum.REDIRECT);
        } finally {
            if (!this.template.getConnectionFactory().getConnection().isClosed()) {
                this.template.getConnectionFactory().getConnection().close();
            }
        }
    }

    @Override
    public boolean exists(String field) {
        return this.exists(image.getData().toString());
    }

}
