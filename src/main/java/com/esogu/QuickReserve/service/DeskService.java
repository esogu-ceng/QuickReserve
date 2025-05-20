package com.esogu.QuickReserve.service;


import com.esogu.QuickReserve.dto.DeskDto;
import com.esogu.QuickReserve.dto.WorkingHoursDto;
import com.esogu.QuickReserve.model.Desk;
import com.esogu.QuickReserve.model.School;
import com.esogu.QuickReserve.model.WorkingHours;
import com.esogu.QuickReserve.repository.DeskRepository;
import com.esogu.QuickReserve.repository.SchoolRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class DeskService {
    private final DeskRepository deskRepository;
    private final SchoolRepository schoolRepository;
    private final AppointmentSlotService slotService;
    private final ModelMapper modelMapper;

    public DeskDto createDesk(Long schoolId, DeskDto deskDto) {
        School school = schoolRepository.findById(schoolId)
                .orElseThrow(() -> new EntityNotFoundException("School not found"));

        // Convert DTO to Entity
        Desk desk = modelMapper.map(deskDto, Desk.class);
        desk.setSchool(school);

        Desk savedDesk = deskRepository.save(desk);

        // Convert Entity back to DTO
        return modelMapper.map(savedDesk, DeskDto.class);
    }

    public List<DeskDto> getDesksBySchool(Long schoolId) {
        return deskRepository.findBySchoolId(schoolId).stream()
                .map(desk -> modelMapper.map(desk, DeskDto.class))
                .toList();
    }

    public void setWorkingHours(Long deskId, List<WorkingHoursDto> workingHoursDtos) {
        Desk desk = deskRepository.findById(deskId)
                .orElseThrow(() -> new EntityNotFoundException("Desk not found"));

        // Clear existing working hours
        desk.getWorkingHours().clear();

        // Convert DTOs to Entities
        List<WorkingHours> workingHours = workingHoursDtos.stream()
                .map(dto -> {
                    WorkingHours wh = modelMapper.map(dto, WorkingHours.class);
                    wh.setDesk(desk);
                    return wh;
                })
                .toList();

        desk.getWorkingHours().addAll(workingHours);
        deskRepository.save(desk);

        slotService.generateSlotsForDesk(deskId);
    }
}
