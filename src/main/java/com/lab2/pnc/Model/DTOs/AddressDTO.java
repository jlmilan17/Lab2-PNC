package com.lab2.pnc.Model.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
    @NotBlank(message = "El departamento es obligatorio")
    @Size(max = 50, message = "El departamento no puede tener más de 50 caracteres")
    private String department;

    private String zone;

    @NotBlank(message = "La calle es obligatoria")
    @Size(max = 100, message = "La calle no puede tener más de 100 caracteres")
    private String street;

    @NotBlank(message = "El municipio es obligatorio")
    @Size(max = 50, message = "El municipio no puede tener más de 50 caracteres")
    private String municipality;

    @NotBlank(message = "La colonia es obligatoria")
    @Size(max = 100, message = "La colonia no puede tener más de 100 caracteres")
    private String neighborhood;

}
