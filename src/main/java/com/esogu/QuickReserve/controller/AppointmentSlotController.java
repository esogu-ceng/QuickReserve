package com.esogu.QuickReserve.controller;


import com.esogu.QuickReserve.dto.AppointmentSlotDto;
import com.esogu.QuickReserve.service.AppointmentSlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/appointment-slot")
@RequiredArgsConstructor
public class AppointmentSlotController {
    private final AppointmentSlotService slotService;

    @GetMapping("/desk/{deskId}/available")
    public ResponseEntity<List<AppointmentSlotDto>> getAvailableSlots(
            @PathVariable Long deskId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {
        return ResponseEntity.ok(slotService.getAvailableSlots(deskId, from, to));
    }

    @PostMapping("/{deskId}/merge")
    public ResponseEntity<Void> mergeSlots(
            @PathVariable Long deskId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {
        slotService.mergeSlots(deskId, startTime, endTime);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{deskId}/special")
    public ResponseEntity<AppointmentSlotDto> addSpecialSlot(
            @PathVariable Long deskId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {
        return ResponseEntity.ok(slotService.addSpecialSlot(deskId, startTime, endTime));
    }

    @DeleteMapping("/{deskId}")
    public ResponseEntity<Void> deleteSlots(
            @PathVariable Long deskId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {
        slotService.deleteSlots(deskId, from, to);
        return ResponseEntity.ok().build();
    }
}
