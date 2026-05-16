package com.lab2.pnc.Model.DTOs;

import lombok.Data;

import java.util.List;

@Data
public class PersonChargesDTO {
    private PersonDTO person;
    private List<ChargesDTO> charges;
}
