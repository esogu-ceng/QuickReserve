package com.esogu.QuickReserve.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Table(name = "appointment_slots")
@Entity
@Data
public class AppointmentSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean isAvailable = true;
    private boolean isSpecial = false;
    private boolean isMerged = false;

    @ManyToOne
    @JoinColumn(name = "desk_id")
    private Desk desk;

    @OneToOne(mappedBy = "slot")
    private Appointment appointment;
}
