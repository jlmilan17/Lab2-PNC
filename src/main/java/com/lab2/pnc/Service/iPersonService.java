package com.lab2.pnc.Service;

import com.lab2.pnc.Model.DTOs.MostWantedDTO;
import com.lab2.pnc.Model.DTOs.PersonChargesDTO;
import com.lab2.pnc.Model.DTOs.PersonDTO;

import java.util.List;

public interface iPersonService {
    PersonDTO registerPerson(PersonDTO personDTO);

    List<PersonDTO> findAllWithCharges();

    List<MostWantedDTO> findMostWanted();

    PersonChargesDTO findChargesOf(String dui);

    List<PersonDTO> findAll();

    PersonDTO findByDui(String dui);

    PersonDTO updatePerson(String dui, PersonDTO personDTO);

    void deletePerson(String dui);
}
