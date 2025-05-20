package com.esogu.QuickReserve.config;


import com.esogu.QuickReserve.dto.DeskDto;
import com.esogu.QuickReserve.dto.WorkingHoursDto;
import com.esogu.QuickReserve.model.Desk;
import com.esogu.QuickReserve.model.WorkingHours;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        return mapper;
    }
}
