package com.lab2.pnc.Model.DTOs;

import com.lab2.pnc.Model.Enum.ChargeStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ChargeStatusDTO {
    @NotNull(message = "El estado del cargo es obligatorio")
    private ChargeStatus status;
}
