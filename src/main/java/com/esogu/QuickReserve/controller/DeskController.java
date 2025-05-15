package com.esogu.QuickReserve.controller;

import com.esogu.QuickReserve.dto.DeskDto;
import com.esogu.QuickReserve.dto.WorkingHoursDto;
import com.esogu.QuickReserve.mapper.DeskMapper;
import com.esogu.QuickReserve.mapper.WorkingHoursMapper;
import com.esogu.QuickReserve.model.Desk;
import com.esogu.QuickReserve.model.WorkingHours;
import com.esogu.QuickReserve.service.DeskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/desk")
public class DeskController {
    private final DeskService deskService;
    private final DeskMapper deskMapper;
    private final WorkingHoursMapper workingHoursMapper;

    @PostMapping("/{schoolId}")
    public ResponseEntity<DeskDto> createDesk(
            @PathVariable Long schoolId,
            @RequestBody DeskDto deskDto) {

        Desk desk = deskMapper.toEntity(deskDto);
        Desk createdDesk = deskService.createDesk(schoolId, desk);
        return ResponseEntity.ok(deskMapper.toDto(createdDesk));
    }

    @GetMapping("/school/{schoolId}")
    public ResponseEntity<List<DeskDto>> getDesksBySchool(@PathVariable Long schoolId) {
        List<Desk> desks = deskService.getDesksBySchool(schoolId);
        List<DeskDto> deskDtos = desks.stream()
                .map(deskMapper::toDto)
                .toList();
        return ResponseEntity.ok(deskDtos);
    }

    @PutMapping("/{deskId}/working-hours")
    public ResponseEntity<Void> setWorkingHours(
            @PathVariable Long deskId,
            @RequestBody List<WorkingHoursDto> workingHoursDtos) {

        List<WorkingHours> workingHours = workingHoursMapper.toEntityList(workingHoursDtos);
        deskService.setWorkingHours(deskId, workingHours);
        return ResponseEntity.ok().build();
    }
}
