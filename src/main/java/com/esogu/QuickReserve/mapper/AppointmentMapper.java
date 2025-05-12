package com.esogu.QuickReserve.mapper;

import com.esogu.QuickReserve.dto.AppointmentDto;
import com.esogu.QuickReserve.model.Appointment;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {
    public AppointmentDto toDto(Appointment appointment) {
        AppointmentDto dto = new AppointmentDto();
        dto.setId(appointment.getId());
        dto.setCreatedAt(appointment.getCreatedAt());
        dto.setStatus(appointment.getStatus());
        return dto;
    }
}
