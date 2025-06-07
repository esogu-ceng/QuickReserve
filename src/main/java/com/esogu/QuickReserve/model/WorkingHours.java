package com.esogu.QuickReserve.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.ZonedDateTime;

@Entity
@Data
@Table(name = "working_hours")
public class WorkingHours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    private ZonedDateTime startTime;
    private ZonedDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "desk_id")
    private Desk desk;
}
