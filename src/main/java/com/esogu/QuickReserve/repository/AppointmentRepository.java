package com.esogu.QuickReserve.repository;

import com.esogu.QuickReserve.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByStudentId(Long studentId);
}
