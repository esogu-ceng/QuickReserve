package com.esogu.QuickReserve.repository;

import com.esogu.QuickReserve.model.AppointmentSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;

public interface AppointmentSlotRepository extends JpaRepository<AppointmentSlot, Long> {

    List<AppointmentSlot> findByDeskIdAndStartTimeBetween(Long deskId, ZonedDateTime start, ZonedDateTime end);
    List<AppointmentSlot> findByDeskIdAndStartTimeBetweenAndIsAvailableTrue(Long schoolId, ZonedDateTime start, ZonedDateTime end);
    List<AppointmentSlot> findByDeskId(Long deskId);
    @Modifying
    @Transactional
    void deleteByDeskIdAndIsSpecialFalse(Long deskId);
}
