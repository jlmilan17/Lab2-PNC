package com.lab2.pnc.Model.DTOs;

import lombok.Data;

@Data
public class MostWantedDTO {
    private PersonDTO person;
    private int score;
}
