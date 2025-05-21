package com.esogu.QuickReserve.dto;

import com.esogu.QuickReserve.enums.AppointmentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDto {
    private Long id;
    private LocalDateTime createdAt;
    private AppointmentStatus status;
}
