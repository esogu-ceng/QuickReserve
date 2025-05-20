package com.esogu.QuickReserve.service;

import com.esogu.QuickReserve.dto.SchoolDto;
import com.esogu.QuickReserve.model.School;
import com.esogu.QuickReserve.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class SchoolService {
    private final SchoolRepository schoolRepository;
    private final ModelMapper modelMapper; // Injected

    public SchoolDto createSchool(SchoolDto schoolDto) {
        School school = modelMapper.map(schoolDto, School.class);
        School savedSchool = schoolRepository.save(school);
        return modelMapper.map(savedSchool, SchoolDto.class);
    }
}
