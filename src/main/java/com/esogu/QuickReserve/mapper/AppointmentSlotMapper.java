package com.esogu.QuickReserve.mapper;

import com.esogu.QuickReserve.dto.AppointmentSlotDto;
import com.esogu.QuickReserve.model.AppointmentSlot;
import org.springframework.stereotype.Component;

@Component
public class AppointmentSlotMapper {
    public AppointmentSlotDto toDto(AppointmentSlot slot) {
        AppointmentSlotDto dto = new AppointmentSlotDto();
        dto.setId(slot.getId());
        dto.setStartTime(slot.getStartTime());
        dto.setEndTime(slot.getEndTime());
        dto.setAvailable(slot.isAvailable());
        dto.setSpecial(slot.isSpecial());
        dto.setMerged(slot.isMerged());
        return dto;
    }

    public AppointmentSlot toEntity(AppointmentSlotDto dto) {
        AppointmentSlot slot = new AppointmentSlot();
        slot.setId(dto.getId());
        slot.setStartTime(dto.getStartTime());
        slot.setEndTime(dto.getEndTime());
        slot.setAvailable(dto.isAvailable());
        slot.setSpecial(dto.isSpecial());
        slot.setMerged(dto.isMerged());
        return slot;
    }
}
