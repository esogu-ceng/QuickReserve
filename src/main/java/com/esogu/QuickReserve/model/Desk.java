package com.esogu.QuickReserve.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "desks")
public class Desk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

    @OneToMany(mappedBy = "desk", cascade = CascadeType.ALL)
    private List<WorkingHours> workingHours = new ArrayList<>();

    @OneToMany(mappedBy = "desk")
    private List<AppointmentSlot> slots = new ArrayList<>();
}
