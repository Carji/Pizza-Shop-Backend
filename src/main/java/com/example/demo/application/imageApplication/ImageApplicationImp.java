package com.example.demo.application.imageApplication;

import java.util.UUID;

import com.example.demo.domain.imageDomain.Image;
import com.example.demo.domain.imageDomain.ImageRepository;
import com.example.demo.core.ApplicationBase;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ImageApplicationImp extends ApplicationBase<Image, UUID> implements ImageApplication{

  
    private final ImageRepository imageRepository;
    private final ModelMapper modelMapper;
    private final Logger logger;

    @Autowired
    public ImageApplicationImp(final ModelMapper modelMapper, final Logger logger,
                                final ImageRepository imageRepository){
            super((id) -> imageRepository.findById(id));
            this.imageRepository=imageRepository;
            this.logger=logger;
            this.modelMapper=modelMapper;
    }

    @Override
    public ImageDTO save(CreateOrUpdateImageDTO dto) {
        Image image = modelMapper.map(dto, Image.class);
        image.setId(UUID.randomUUID());
        image.validate();
        ImageDTO imageDTO=this.imageRepository.save(image);
        this.logger.info(serializeObject(image, "added"));
        return imageDTO;
    } 
    
    @Override
    public ImageDTOOut get(UUID id){
        Image image= this.findById(id);
        ImageDTOOut imageDTOOut= this.modelMapper.map(image, ImageDTOOut.class);
        return imageDTOOut;
    }


    protected String serializeObject(Image image, String msg, String msg2){
        return String.format("Image {id: %s, data: %s} has been %s succesfully.",
                            image.getId(), image.getData(),
                            msg);
    }

}
