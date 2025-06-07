package com.esogu.QuickReserve.service;


import com.esogu.QuickReserve.dto.AppointmentDto;
import com.esogu.QuickReserve.dto.AppointmentSlotDto;
import com.esogu.QuickReserve.enums.AppointmentStatus;
import com.esogu.QuickReserve.model.Appointment;
import com.esogu.QuickReserve.model.AppointmentSlot;
import com.esogu.QuickReserve.model.Student;
import com.esogu.QuickReserve.repository.AppointmentRepository;
import com.esogu.QuickReserve.repository.AppointmentSlotRepository;
import com.esogu.QuickReserve.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final AppointmentSlotRepository slotRepository;
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public AppointmentDto createAppointment(Long studentId, Long slotId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));

        AppointmentSlot slot = slotRepository.findById(slotId)
                .orElseThrow(() -> new EntityNotFoundException("Slot not found"));

        if (!slot.isAvailable()) {
            throw new IllegalStateException("Slot is not available");
        }

        LocalDateTime now = LocalDateTime.now();
        if (slot.getStartTime().isBefore(now.plusHours(1))) {
            throw new IllegalStateException("Appointments must be made at least 1 hour in advance");
        }

        Appointment appointment = new Appointment();
        appointment.setStudent(student);
        appointment.setSlot(slot);
        appointment.setCreatedAt(now);
        appointment.setStatus(AppointmentStatus.SCHEDULED);

        slot.setAvailable(false);
        slotRepository.save(slot);

        Appointment savedAppointment = appointmentRepository.save(appointment);
        return modelMapper.map(savedAppointment, AppointmentDto.class);
    }

    @Transactional
    public void cancelAppointment(Long appointmentId, Long studentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found"));

        // Verify student owns the appointment
        if (!appointment.getStudent().getId().equals(studentId)) {
            throw new SecurityException("Student does not own this appointment");
        }

        // Check cancellation window (e.g., 30 minutes before)
        LocalDateTime now = LocalDateTime.now();
        if (appointment.getSlot().getStartTime().isBefore(now.plusMinutes(30))) {
            throw new IllegalStateException("Appointments can only be cancelled at least 30 minutes before");
        }

        // Mark slot as available
        AppointmentSlot slot = appointment.getSlot();
        slot.setAvailable(true);
        slotRepository.save(slot);

        // Update appointment status
        appointment.setStatus(AppointmentStatus.CANCELLED);
        appointmentRepository.save(appointment);
    }

    public List<AppointmentDto> getStudentAppointments(Long studentId) {
        return appointmentRepository.findByStudentId(studentId).stream()
                .map(appointment -> modelMapper.map(appointment, AppointmentDto.class))
                .toList();
    }

    public AppointmentSlotDto findNextAvailableSlot(Long schoolId) {
        LocalDateTime now = LocalDateTime.now();
        List<AppointmentSlot> availableSlots = slotRepository
                .findByDeskSchoolIdAndStartTimeBetweenAndIsAvailableTrue(
                        schoolId,
                        now.plusMinutes(30),
                        now.plusWeeks(2)
                );

        AppointmentSlot slot = availableSlots.stream()
                .min(Comparator.comparing(AppointmentSlot::getStartTime))
                .orElseThrow(() -> new EntityNotFoundException("No available slots found"));

        return modelMapper.map(slot, AppointmentSlotDto.class);
    }
}
