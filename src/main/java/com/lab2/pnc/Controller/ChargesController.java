package com.lab2.pnc.Controller;

import com.lab2.pnc.Model.Charges;
import com.lab2.pnc.Model.DTOs.ChargeRequestDTO;
import com.lab2.pnc.Model.DTOs.ChargeSummaryDTO;
import com.lab2.pnc.Model.DTOs.ChargesDTO;
import com.lab2.pnc.Service.iChargesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @GetMapping("/{id}")
    ResponseEntity<Charges> getCharge(@PathVariable String id) {
        UUID chargeId = UUID.fromString(id);
        return ResponseEntity.ok(chargesService.findById(chargeId));
    }
}
