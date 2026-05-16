package com.lab2.pnc.Model.DTOs;

import com.lab2.pnc.Model.Enum.ChargeType;
import lombok.Data;

@Data
public class ChargeSummaryDTO {
    private ChargeType chargeType;
    private PersonSummaryDTO accuser;
    private String officerName;
}
