package com.esogu.QuickReserve.service;


import com.esogu.QuickReserve.model.Desk;
import com.esogu.QuickReserve.model.School;
import com.esogu.QuickReserve.model.WorkingHours;
import com.esogu.QuickReserve.repository.DeskRepository;
import com.esogu.QuickReserve.repository.SchoolRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class DeskService {
    private final DeskRepository deskRepository;
    private final SchoolRepository schoolRepository;
    private final AppointmentSlotService slotService;

    public Desk createDesk(Long schoolId, Desk desk) {
        School school = schoolRepository.findById(schoolId)
                .orElseThrow(() -> new EntityNotFoundException("School not found"));
        desk.setSchool(school);
        return deskRepository.save(desk);
    }

    public List<Desk> getDesksBySchool(Long schoolId) {
        return deskRepository.findBySchoolId(schoolId);
    }

    public void setWorkingHours(Long deskId, List<WorkingHours> workingHours) {
        Desk desk = deskRepository.findById(deskId)
                .orElseThrow(() -> new EntityNotFoundException("Desk not found"));

        // Clear existing working hours
        desk.getWorkingHours().clear();

        // Set new working hours
        workingHours.forEach(wh -> wh.setDesk(desk));
        desk.getWorkingHours().addAll(workingHours);

        deskRepository.save(desk);

        // Generate slots based on new working hours
        slotService.generateSlotsForDesk(deskId);
    }
}
