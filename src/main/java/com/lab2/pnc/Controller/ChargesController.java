package com.lab2.pnc.Controller;

import com.lab2.pnc.Model.DTOs.ChargeRequestDTO;
import com.lab2.pnc.Model.DTOs.ChargeSummaryDTO;
import com.lab2.pnc.Model.DTOs.ChargeStatusDTO;
import com.lab2.pnc.Model.DTOs.ChargeUpdateDTO;
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
    ResponseEntity<ChargesDTO> getCharge(@PathVariable String id) {
        return ResponseEntity.ok(chargesService.findById(UUID.fromString(id)));
    }

    @PutMapping("/{id}")
    ResponseEntity<ChargesDTO> updateCharge(@PathVariable String id, @Valid @RequestBody ChargeUpdateDTO dto) {
        return ResponseEntity.ok(chargesService.updateCharge(UUID.fromString(id), dto));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteCharge(@PathVariable String id) {
        chargesService.deleteCharge(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/status")
    ResponseEntity<ChargesDTO> updateChargeStatus(@PathVariable String id, @Valid @RequestBody ChargeStatusDTO dto) {
        return ResponseEntity.ok(chargesService.updateChargeStatus(UUID.fromString(id), dto));
    }
}
