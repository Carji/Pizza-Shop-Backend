package com.example.demo.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.*;

@Configuration
public class MapperConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }
}
