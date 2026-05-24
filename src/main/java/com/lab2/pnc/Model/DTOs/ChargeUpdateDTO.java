package com.lab2.pnc.Model.DTOs;

import com.lab2.pnc.Model.Enum.ChargeType;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class ChargeUpdateDTO {
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @PastOrPresent(message = "La fecha no puede ser futura")
    private LocalDateTime date;

    private ChargeType chargeType;

    @Size(max = 1000, message = "La descripción no puede tener más de 1000 caracteres")
    private String description;
}
