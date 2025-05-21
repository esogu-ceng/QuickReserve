package com.esogu.QuickReserve.repository;

import com.esogu.QuickReserve.model.AppointmentSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentSlotRepository extends JpaRepository<AppointmentSlot, Long> {

    List<AppointmentSlot> findByDeskIdAndStartTimeBetween(Long deskId, LocalDateTime start, LocalDateTime end);
    List<AppointmentSlot> findByDeskIdAndStartTimeBetweenAndIsAvailableTrue(Long deskId, LocalDateTime start, LocalDateTime end);
    List<AppointmentSlot> findByDeskSchoolIdAndStartTimeBetweenAndIsAvailableTrue (Long schoolId, LocalDateTime start, LocalDateTime end);
    @Modifying
    @Transactional
    void deleteByDeskIdAndIsSpecialFalse(Long deskId);
}
