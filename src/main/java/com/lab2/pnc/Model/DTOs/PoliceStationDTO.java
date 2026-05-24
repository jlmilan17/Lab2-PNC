package com.lab2.pnc.Model.DTOs;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PoliceStationDTO {
    @NotBlank(message = "El nombre de la estación de policía es obligatorio")
    @Size(max = 100, message = "El nombre de la estación de policía no puede tener más de 100 caracteres")
    private String name;

    @Valid
    @NotNull(message = "La dirección es obligatoria")
    private AddressDTO addressDTO;

    private PoliceOfficerDTO director;
}
