package com.lab2.pnc.Model.DTOs;

import com.lab2.pnc.Model.Enum.ChargeType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ChargeStatusDTO {
    @NotNull(message = "El tipo de cargo es obligatorio")
    private ChargeType chargeType;
}
