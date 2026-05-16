package com.lab2.pnc.Model.DTOs;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PoliceOfficerDTO {
    @Valid
    @NotNull(message = "La información personal es obligatoria")
    private PersonDTO personDTO;

    @NotBlank(message = "El código es obligatorio")
    @Size(max = 20, message = "El código no puede tener más de 20 caracteres")
    private String code;

    @NotBlank(message = "La insignia es obligatoria")
    @Size(max = 20, message = "La insignia no puede tener más de 20 caracteres")
    private String badge;

    @Valid
    @NotNull(message = "La estación de policía es obligatoria")
    private PoliceStationDTO policeStationDTO;
}
