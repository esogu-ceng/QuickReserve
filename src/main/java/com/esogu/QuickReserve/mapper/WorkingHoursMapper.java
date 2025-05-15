package com.esogu.QuickReserve.mapper;

import com.esogu.QuickReserve.dto.WorkingHoursDto;
import com.esogu.QuickReserve.model.WorkingHours;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WorkingHoursMapper {
    public WorkingHoursDto toDto(WorkingHours entity) {
        return new WorkingHoursDto(
                entity.getId(),
                entity.getDayOfWeek(),
                entity.getStartTime(),
                entity.getEndTime()
        );
    }

    public WorkingHours toEntity(WorkingHoursDto dto) {
        WorkingHours entity = new WorkingHours();
        entity.setId(dto.getId());
        entity.setDayOfWeek(dto.getDayOfWeek());
        entity.setStartTime(dto.getStartTime());
        entity.setEndTime(dto.getEndTime());
        return entity;
    }

    public List<WorkingHoursDto> toDtoList(List<WorkingHours> entities) {
        return entities.stream()
                .map(this::toDto)
                .toList();
    }

    public List<WorkingHours> toEntityList(List<WorkingHoursDto> dtos) {
        return dtos.stream()
                .map(this::toEntity)
                .toList();
    }
}
