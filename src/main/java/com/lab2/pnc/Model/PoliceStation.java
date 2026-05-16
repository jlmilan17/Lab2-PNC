package com.lab2.pnc.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PoliceStation {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID policeStationUuid;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToOne
    @JoinColumn(name = "director_id", unique = true)
    private PoliceOfficer director;
}
