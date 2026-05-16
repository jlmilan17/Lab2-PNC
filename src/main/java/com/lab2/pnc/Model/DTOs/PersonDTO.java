package com.lab2.pnc.Model.DTOs;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PersonDTO {
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\\s]+$", message = "El nombre solo puede contener letras y espacios")
    private String name;

    @NotBlank(message = "El DUI es obligatorio")
    @Pattern(regexp = "\\d{8}-\\d", message = "El DUI debe tener el formato 12345678-9")
    private String dui;

    @Valid
    @NotNull(message = "La dirección es obligatoria")
    private AddressDTO addressDTO;

    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "\\d{4}-\\d{4}", message = "El teléfono debe tener el formato 7777-7777")
    private String phoneNumber;
}
