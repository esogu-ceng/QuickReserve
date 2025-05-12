package com.esogu.QuickReserve.mapper;

import com.esogu.QuickReserve.dto.SchoolDto;
import com.esogu.QuickReserve.model.School;
import org.springframework.stereotype.Component;

@Component
public class SchoolMapper {
    public SchoolDto toDto(School school) {
        return new SchoolDto(
                school.getId(),
                school.getName(),
                school.getTimezone()
        );
    }

    public School toEntity(SchoolDto dto) {
        School school = new School();
        school.setId(dto.getId());
        school.setName(dto.getName());
        school.setTimezone(dto.getTimezone());
        return school;
    }
}
