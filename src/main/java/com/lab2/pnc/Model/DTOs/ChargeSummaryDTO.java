package com.lab2.pnc.Model.DTOs;

import com.lab2.pnc.Model.Enum.ChargeType;
import lombok.Data;

import java.util.UUID;

@Data
public class ChargeSummaryDTO {
    private UUID id;
    private ChargeType chargeType;
    private PersonSummaryDTO accuser;
    private String officerName;
}
