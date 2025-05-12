package com.esogu.QuickReserve.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String timezone;

    @OneToMany(mappedBy = "student")
    private List<Appointment> appointments = new ArrayList<>();
}
