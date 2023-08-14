package com.transtour.backend.utils;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperSpring {

	@Bean
	ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
