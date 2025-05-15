package com.esogu.QuickReserve.service;

import com.esogu.QuickReserve.model.School;
import com.esogu.QuickReserve.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class SchoolService {
    private final SchoolRepository schoolRepository;

    public School createSchool(School school) {
        return schoolRepository.save(school);
    }

}
