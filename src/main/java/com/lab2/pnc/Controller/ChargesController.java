package com.lab2.pnc.Controller;

import com.lab2.pnc.Model.DTOs.ChargeRequestDTO;
import com.lab2.pnc.Model.DTOs.ChargeSummaryDTO;
import com.lab2.pnc.Model.DTOs.ChargesDTO;
import com.lab2.pnc.Service.iChargesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/charges")
@RequiredArgsConstructor
public class ChargesController {
    private final iChargesService chargesService;

    @PostMapping("/register")
    ResponseEntity<ChargesDTO> registerCharge(@Valid @RequestBody ChargeRequestDTO request) {
        return ResponseEntity.ok(chargesService.registerCharge(request));
    }

    @GetMapping
    ResponseEntity<List<ChargeSummaryDTO>> getAllChargesSummary() {
        return ResponseEntity.ok(chargesService.getAllChargesSummary());
    }
}
