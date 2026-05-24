package com.lab2.pnc.Model.DTOs;

import com.lab2.pnc.Model.Enum.ChargeType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class ChargesDTO {
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @NotNull(message = "La fecha es obligatoria")
    @PastOrPresent(message = "La fecha no puede ser futura")
    private LocalDateTime date;

    @NotNull(message = "El tipo de cargo es obligatorio")
    private ChargeType chargeType;

    @Valid
    @NotNull(message = "El acusador es obligatorio")
    private PersonDTO accuser;

    @Valid
    @NotNull(message = "El acusado es obligatorio")
    private PersonDTO accused;

    @Valid
    @NotNull(message = "El oficial de policía que registró es obligatorio")
    private PoliceOfficerDTO registeredBy;

    @Valid
    @NotNull(message = "La estación de policía es obligatoria")
    private PoliceStationDTO policeStation;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(max = 255, message = "La descripción no puede tener más de 255 caracteres")
    private String description;

}
