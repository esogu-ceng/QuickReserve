package com.esogu.QuickReserve.service;


import com.esogu.QuickReserve.enums.AppointmentStatus;
import com.esogu.QuickReserve.model.Appointment;
import com.esogu.QuickReserve.model.AppointmentSlot;
import com.esogu.QuickReserve.model.Student;
import com.esogu.QuickReserve.repository.AppointmentRepository;
import com.esogu.QuickReserve.repository.AppointmentSlotRepository;
import com.esogu.QuickReserve.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;

import lombok.RequiredArgsConstructor;
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

    @Transactional
    public Appointment createAppointment(Long studentId, Long slotId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));

        AppointmentSlot slot = slotRepository.findById(slotId)
                .orElseThrow(() -> new EntityNotFoundException("Slot not found"));

        // Check if slot is available
        if (!slot.isAvailable()) {
            throw new IllegalStateException("Slot is not available");
        }

        // Check minimum time before appointment (e.g., 1 hour)
        LocalDateTime now = LocalDateTime.now();
        if (slot.getStartTime().isBefore(now.plusHours(1))) {
            throw new IllegalStateException("Appointments must be made at least 1 hour in advance");
        }

        // Create appointment
        Appointment appointment = new Appointment();
        appointment.setStudent(student);
        appointment.setSlot(slot);
        appointment.setCreatedAt(now);
        appointment.setStatus(AppointmentStatus.SCHEDULED);

        // Mark slot as unavailable
        slot.setAvailable(false);
        slotRepository.save(slot);

        return appointmentRepository.save(appointment);
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

    public List<Appointment> getStudentAppointments(Long studentId) {
        return appointmentRepository.findByStudentId(studentId);
    }

    public AppointmentSlot findNextAvailableSlot(Long schoolId) {
        LocalDateTime now = LocalDateTime.now();
        List<AppointmentSlot> availableSlots = slotRepository
                .findByDeskSchoolIdAndStartTimeBetweenAndIsAvailableTrue(
                        schoolId,
                        now.plusMinutes(30), // Don't show slots starting too soon
                        now.plusWeeks(2)    // Look ahead 2 weeks
                );

        return availableSlots.stream()
                .min(Comparator.comparing(AppointmentSlot::getStartTime))
                .orElseThrow(() -> new EntityNotFoundException("No available slots found"));
    }
}
