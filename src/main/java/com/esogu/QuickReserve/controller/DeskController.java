package com.esogu.QuickReserve.controller;

import com.esogu.QuickReserve.dto.DeskDto;
import com.esogu.QuickReserve.dto.WorkingHoursDto;
import com.esogu.QuickReserve.service.DeskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/desk")
public class DeskController {
    private final DeskService deskService; // Removed mappers

    @PostMapping("/{schoolId}")
    public ResponseEntity<DeskDto> createDesk(
            @PathVariable Long schoolId,
            @RequestBody DeskDto deskDto) {
        return ResponseEntity.ok(deskService.createDesk(schoolId, deskDto));
    }

    @GetMapping("/school/{schoolId}")
    public ResponseEntity<List<DeskDto>> getDesksBySchool(@PathVariable Long schoolId) {
        return ResponseEntity.ok(deskService.getDesksBySchool(schoolId));
    }

    @PutMapping("/{deskId}/working-hours")
    public ResponseEntity<Void> setWorkingHours(
            @PathVariable Long deskId,
            @RequestBody List<WorkingHoursDto> workingHoursDtos) {
        deskService.setWorkingHours(deskId, workingHoursDtos);
        return ResponseEntity.ok().build();
    }
}
