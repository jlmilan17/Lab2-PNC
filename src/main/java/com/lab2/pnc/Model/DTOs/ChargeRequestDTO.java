package com.lab2.pnc.Model.DTOs;

import com.lab2.pnc.Model.Enum.ChargeType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class ChargeRequestDTO {
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @NotNull(message = "La fecha es obligatoria")
    @PastOrPresent(message = "La fecha no puede ser futura")
    private LocalDateTime date;

    @NotNull(message = "El tipo de cargo es obligatorio")
    private ChargeType chargeType;

    @NotBlank(message = "El DUI del acusador es obligatorio")
    @Pattern(regexp = "\\d{8}-\\d", message = "El DUI del acusador debe tener el formato 12345678-9")
    private String accuserDui;

    @NotBlank(message = "El DUI del acusado es obligatorio")
    @Pattern(regexp = "\\d{8}-\\d", message = "El DUI del acusado debe tener el formato 12345678-9")
    private String accusedDui;

    @NotBlank(message = "El código del oficial es obligatorio")
    @Size(max = 20, message = "El código del oficial no puede tener más de 20 caracteres")
    private String officerCode;

    @NotBlank(message = "El nombre de la estación es obligatorio")
    @Size(max = 100, message = "El nombre de la estación no puede tener más de 100 caracteres")
    private String policeStationName;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(max = 255, message = "La descripción no puede tener más de 255 caracteres")
    private String description;
}
