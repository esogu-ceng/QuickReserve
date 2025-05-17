package com.esogu.QuickReserve.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentSlotDto {
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean isAvailable;
    private boolean isSpecial;
    private boolean isMerged;
}
