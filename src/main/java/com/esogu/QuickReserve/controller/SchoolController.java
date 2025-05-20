package com.esogu.QuickReserve.controller;

import com.esogu.QuickReserve.dto.SchoolDto;
import com.esogu.QuickReserve.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api/school")
public class SchoolController {
    private final SchoolService schoolService;

    @PostMapping
    public ResponseEntity<SchoolDto> createSchool(
            @RequestBody SchoolDto schoolDto) {
        return ResponseEntity.ok(schoolService.createSchool(schoolDto));
    }

}
