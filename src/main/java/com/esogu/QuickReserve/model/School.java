package com.esogu.QuickReserve.model;


import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table (name= "schools")
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "school_id")
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    // One school has many desks
    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL)
    private List<Desk> desks = new ArrayList<>();
}
