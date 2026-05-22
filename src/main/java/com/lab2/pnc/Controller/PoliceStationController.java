package com.lab2.pnc.Controller;


import com.lab2.pnc.Model.DTOs.PoliceStationDTO;
import com.lab2.pnc.Service.iPoliceStationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/police-stations")
@RequiredArgsConstructor
public class PoliceStationController {
    private final iPoliceStationService policeStationService;

    @GetMapping("")
    ResponseEntity<List<PoliceStationDTO>> findAll() { return ResponseEntity.ok(policeStationService.findAll());}
}
