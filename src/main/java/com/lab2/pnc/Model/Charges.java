package com.lab2.pnc.Model;

import com.lab2.pnc.Model.Enum.ChargeType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Charges {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID chargesUuid;

    @Column(nullable = false)
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ChargeType chargeType;

    @ManyToOne
    @JoinColumn(name = "accuser_id", nullable = false)
    private Person accuser;

    @ManyToOne
    @JoinColumn(name = "accused_id", nullable = false)
    private Person accused;

    @ManyToOne
    @JoinColumn(name = "registered_by_officer_id", nullable = false)
    private PoliceOfficer registeredBy;

    @ManyToOne
    @JoinColumn(name = "police_station_id", nullable = false)
    private PoliceStation policeStation;

    @Column(nullable = false, length = 1000)
    private String description;
}
