package com.esogu.QuickReserve.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentSlotDto {
    private Long id;
    private ZonedDateTime startTime;
    private ZonedDateTime endTime;
    private boolean isAvailable;
    private boolean isSpecial;
    private boolean isMerged;
}
