package com.esogu.QuickReserve.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Duration;
import java.util.List;


@Entity
@Table(name="desks")
@Data
public class Desk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "desk_id")
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "minimum_booking_notice")
    private Duration minimumBookingNotice;    // e.g., 30 minutes

    @Column(name = "min_gap_between_bookings")
    private Duration minGapBetweenBookings;   // e.g., 24 hours

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

    @OneToMany(mappedBy = "desk", cascade = CascadeType.ALL)
    private List<AvailabilitySlot> normalAvailability;

    @OneToMany(mappedBy = "desk", cascade = CascadeType.ALL)
    private List<SpecialAvailability> specialAvailabilities;

    @OneToMany(mappedBy = "desk", cascade = CascadeType.ALL)
    private List<Appointment> appointments;
}
