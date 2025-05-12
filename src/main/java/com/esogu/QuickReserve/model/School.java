package com.esogu.QuickReserve.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "schools")
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String timezone; // e.g., "America/New_York"

    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL)
    private List<Desk> desks = new ArrayList<>();

}