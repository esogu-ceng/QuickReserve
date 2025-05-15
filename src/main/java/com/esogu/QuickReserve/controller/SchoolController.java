package com.esogu.QuickReserve.controller;

import com.esogu.QuickReserve.dto.SchoolDto;
import com.esogu.QuickReserve.mapper.SchoolMapper;
import com.esogu.QuickReserve.model.School;
import com.esogu.QuickReserve.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api/school")
public class SchoolController {
    private final SchoolService schoolService;
    private final SchoolMapper schoolMapper;

    @PostMapping
    public ResponseEntity<SchoolDto> createSchool(@RequestBody SchoolDto schoolDto) {
        School school = schoolMapper.toEntity(schoolDto);
        School createdSchool = schoolService.createSchool(school);
        return ResponseEntity.ok(schoolMapper.toDto(createdSchool));
    }

}
