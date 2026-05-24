package com.lab2.pnc.Model.DTOs;

import com.lab2.pnc.Model.Enum.ChargeStatus;
import com.lab2.pnc.Model.Enum.ChargeType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChargesDTO {
    private LocalDateTime date;
    private ChargeType chargeType;
    private ChargeStatus status;
    private String description;
    private PersonDTO accuser;
    private PersonDTO accused;
    private PoliceOfficerDTO registeredBy;
    private PoliceStationDTO policeStation;
}
