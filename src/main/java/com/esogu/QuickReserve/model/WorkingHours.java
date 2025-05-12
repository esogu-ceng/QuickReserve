package com.esogu.QuickReserve.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.TypeAlias;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Data
@Table(name = "working_hours")
public class WorkingHours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    private LocalTime startTime;
    private LocalTime endTime;

    @ManyToOne
    @JoinColumn(name = "desk_id")
    private Desk desk;
}
