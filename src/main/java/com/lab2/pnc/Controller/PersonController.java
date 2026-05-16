package com.lab2.pnc.Controller;

import com.lab2.pnc.Model.DTOs.MostWantedDTO;
import com.lab2.pnc.Model.DTOs.PersonChargesDTO;
import com.lab2.pnc.Model.DTOs.PersonDTO;
import com.lab2.pnc.Service.iPersonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class PersonController {
    private final iPersonService personService;

    @PostMapping("/register")
    ResponseEntity<PersonDTO> registerPerson(@Valid @RequestBody PersonDTO personDTO) {
        return ResponseEntity.ok(personService.registerPerson(personDTO));
    }

    @GetMapping("/charged")
    ResponseEntity<List<PersonDTO>> findAllWithCharges() {
        return ResponseEntity.ok(personService.findAllWithCharges());
    }

    @GetMapping("/most-wanted")
    ResponseEntity<List<MostWantedDTO>> findMostWanted() {
        return ResponseEntity.ok(personService.findMostWanted());
    }

    @GetMapping("/{dui}/charges")
    ResponseEntity<PersonChargesDTO> findChargesOf(@PathVariable String dui) {
        return ResponseEntity.ok(personService.findChargesOf(dui));
    }
}
