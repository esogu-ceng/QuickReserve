package com.esogu.QuickReserve.controller;

import com.esogu.QuickReserve.dto.AppointmentDto;
import com.esogu.QuickReserve.dto.AppointmentSlotDto;
import com.esogu.QuickReserve.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/appointment")
public class AppointmentController {
    private final AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<AppointmentDto> createAppointment(
            @RequestParam Long studentId,
            @RequestParam Long slotId) {
        return ResponseEntity.ok(appointmentService.createAppointment(studentId, slotId));
    }

    @DeleteMapping("/{appointmentId}")
    public ResponseEntity<Void> cancelAppointment(
            @PathVariable Long appointmentId,
            @RequestParam Long studentId) {
        appointmentService.cancelAppointment(appointmentId, studentId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<AppointmentDto>> getStudentAppointments(
            @PathVariable Long studentId) {
        return ResponseEntity.ok(appointmentService.getStudentAppointments(studentId));
    }

    @GetMapping("/school/{schoolId}/next-available")
    public ResponseEntity<AppointmentSlotDto> findNextAvailableSlot(
            @PathVariable Long schoolId) {
        return ResponseEntity.ok(appointmentService.findNextAvailableSlot(schoolId));
    }
}
