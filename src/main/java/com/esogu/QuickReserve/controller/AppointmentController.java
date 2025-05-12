package com.esogu.QuickReserve.controller;

import com.esogu.QuickReserve.dto.AppointmentDto;
import com.esogu.QuickReserve.dto.AppointmentSlotDto;
import com.esogu.QuickReserve.mapper.AppointmentMapper;
import com.esogu.QuickReserve.mapper.AppointmentSlotMapper;
import com.esogu.QuickReserve.model.Appointment;
import com.esogu.QuickReserve.model.AppointmentSlot;
import com.esogu.QuickReserve.service.AppointmentService;
import com.esogu.QuickReserve.service.AppointmentSlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping ("/api/appointment")
public class AppointmentController {
    private final AppointmentService appointmentService;
    private final AppointmentMapper appointmentMapper;
    private final AppointmentSlotMapper appointmentSlotMapper;

    @PostMapping
    public ResponseEntity<AppointmentDto> createAppointment(
            @RequestParam Long studentId,
            @RequestParam Long slotId) {
        var appointment = appointmentService.createAppointment(studentId, slotId);
        return ResponseEntity.ok(appointmentMapper.toDto(appointment));
    }

    @DeleteMapping("/{appointmentId}")
    public ResponseEntity<Void> cancelAppointment(
            @PathVariable Long appointmentId,
            @RequestParam Long studentId) {
        appointmentService.cancelAppointment(appointmentId, studentId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<AppointmentDto>> getStudentAppointments(@PathVariable Long studentId) {
        var appointments = appointmentService.getStudentAppointments(studentId)
                .stream()
                .map(appointmentMapper::toDto)
                .toList();
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/school/{schoolId}/next-available")
    public ResponseEntity<AppointmentSlotDto> findNextAvailableSlot(
            @PathVariable Long schoolId) {
        AppointmentSlot slot = appointmentService.findNextAvailableSlot(schoolId);
        return ResponseEntity.ok(appointmentSlotMapper.toDto(slot));
    }
}
