package com.esogu.QuickReserve.model;

import com.esogu.QuickReserve.enums.AppointmentStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Entity
@Data
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "slot_id")
    private AppointmentSlot slot;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    private ZonedDateTime createdAt;
    private AppointmentStatus status;
}
