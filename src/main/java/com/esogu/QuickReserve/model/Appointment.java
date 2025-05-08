package com.esogu.QuickReserve.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

@Entity
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id")
    private Long id;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @ManyToOne
    @JoinColumn(name = "desk_id")
    private Desk desk;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    // If needed, you can also store timezone here
    @Column(name = "time_zone", nullable = false)
    private ZoneId timeZone;
}
