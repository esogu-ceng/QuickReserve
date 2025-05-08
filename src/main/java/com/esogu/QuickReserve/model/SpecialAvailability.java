package com.esogu.QuickReserve.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Table(name = "special_availabilities")
@Data
public class SpecialAvailability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "special_availability_id")
    private Long id;

    @Column(name = "date", nullable = false)
    private LocalDate date; // specific day this applies to

    @Column(name = "available", nullable = false)
    private boolean available;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    @ManyToOne
    @JoinColumn(name = "desk_id")
    private Desk desk;
}
