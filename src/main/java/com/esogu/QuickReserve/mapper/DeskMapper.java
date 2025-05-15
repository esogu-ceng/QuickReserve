package com.esogu.QuickReserve.mapper;

import com.esogu.QuickReserve.dto.DeskDto;
import com.esogu.QuickReserve.model.Desk;
import org.springframework.stereotype.Component;

@Component
public class DeskMapper {
    public DeskDto toDto(Desk desk) {
        DeskDto dto = new DeskDto();
        dto.setId(desk.getId());
        dto.setName(desk.getName());
        return dto;
    }

    public Desk toEntity(DeskDto dto) {
        Desk desk = new Desk();
        desk.setId(dto.getId()); // Only for updates
        desk.setName(dto.getName());
        return desk;
    }
}
