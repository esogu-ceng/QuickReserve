package com.esogu.QuickReserve.repository;

import com.esogu.QuickReserve.model.AppointmentSlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentSlotRepository extends JpaRepository<AppointmentSlot, Long> {

    List<AppointmentSlot> findByDeskIdAndStartTimeBetween(Long deskId, LocalDateTime start, LocalDateTime end);
    List<AppointmentSlot> findByDeskIdAndStartTimeBetweenAndIsAvailableTrue(Long schoolId, LocalDateTime start, LocalDateTime end);
    List<AppointmentSlot> findByDeskId(Long deskId);
    List<AppointmentSlot> findByDeskSchoolIdAndStartTimeBetweenAndIsAvailableTrue (Long schoolId, LocalDateTime start, LocalDateTime end);
}
