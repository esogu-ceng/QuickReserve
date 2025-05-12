package com.esogu.QuickReserve.service;

import com.esogu.QuickReserve.model.AppointmentSlot;
import com.esogu.QuickReserve.model.Desk;
import com.esogu.QuickReserve.model.WorkingHours;
import com.esogu.QuickReserve.repository.AppointmentSlotRepository;
import com.esogu.QuickReserve.repository.DeskRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentSlotService {
    private final AppointmentSlotRepository slotRepository;
    private final DeskRepository deskRepository;

    public void generateSlotsForDesk(Long deskId) {
        Desk desk = deskRepository.findById(deskId)
                .orElseThrow(() -> new EntityNotFoundException("Desk not found"));

        // Clear existing regular slots (keep special slots)
        List<AppointmentSlot> existingSlots = slotRepository.findByDeskId(deskId);
        existingSlots.stream()
                .filter(slot -> !slot.isSpecial())
                .forEach(slotRepository::delete);

        // Generate new slots based on working hours
        for (WorkingHours wh : desk.getWorkingHours()) {
            generateSlotsForWorkingHours(desk, wh);
        }
    }

    private void generateSlotsForWorkingHours(Desk desk, WorkingHours wh) {
        // Default slot duration - could be configurable per desk
        Duration slotDuration = Duration.ofMinutes(30);

        LocalTime current = wh.getStartTime();
        while (current.plus(slotDuration).isBefore(wh.getEndTime()) || current.plus(slotDuration).equals(wh.getEndTime())) {
            LocalDateTime start = LocalDateTime.now()
                    .with(wh.getDayOfWeek())
                    .withHour(current.getHour())
                    .withMinute(current.getMinute())
                    .withSecond(0)
                    .withNano(0);

            LocalDateTime end = start.plus(slotDuration);

            AppointmentSlot slot = new AppointmentSlot();
            slot.setDesk(desk);
            slot.setStartTime(start);
            slot.setEndTime(end);
            slot.setAvailable(true);
            slot.setSpecial(false);

            slotRepository.save(slot);

            current = current.plus(slotDuration);
        }
    }

    public void mergeSlots(Long deskId, LocalDateTime startTime, LocalDateTime endTime) {
        List<AppointmentSlot> slotsToMerge = slotRepository.findByDeskIdAndStartTimeBetween(deskId, startTime, endTime.minusMinutes(1));

        if (slotsToMerge.isEmpty()) {
            throw new IllegalArgumentException("No slots found to merge in the given time range");
        }

        // Delete all slots except the first one
        for (int i = 1; i < slotsToMerge.size(); i++) {
            slotRepository.delete(slotsToMerge.get(i));
        }

        // Update the first slot to cover the entire merged duration
        AppointmentSlot mergedSlot = slotsToMerge.get(0);
        mergedSlot.setEndTime(endTime);
        mergedSlot.setMerged(true);
        slotRepository.save(mergedSlot);
    }

    public AppointmentSlot addSpecialSlot(Long deskId, LocalDateTime startTime, LocalDateTime endTime) {
        Desk desk = deskRepository.findById(deskId)
                .orElseThrow(() -> new EntityNotFoundException("Desk not found"));

        AppointmentSlot slot = new AppointmentSlot();
        slot.setDesk(desk);
        slot.setStartTime(startTime);
        slot.setEndTime(endTime);
        slot.setAvailable(true);
        slot.setSpecial(true);

        return slotRepository.save(slot);
    }

    public void deleteSlots(Long deskId, LocalDateTime from, LocalDateTime to) {
        List<AppointmentSlot> slotsToDelete = slotRepository.findByDeskIdAndStartTimeBetween(deskId, from, to);
        slotRepository.deleteAll(slotsToDelete);
    }

    public List<AppointmentSlot> getAvailableSlots(Long deskId, LocalDateTime from, LocalDateTime to) {
        return slotRepository.findByDeskIdAndStartTimeBetweenAndIsAvailableTrue(deskId, from, to);
    }
}
