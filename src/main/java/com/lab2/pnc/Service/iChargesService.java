package com.lab2.pnc.Service;

import com.lab2.pnc.Model.DTOs.ChargeRequestDTO;
import com.lab2.pnc.Model.DTOs.ChargeSummaryDTO;
import com.lab2.pnc.Model.DTOs.ChargeStatusDTO;
import com.lab2.pnc.Model.DTOs.ChargeUpdateDTO;
import com.lab2.pnc.Model.DTOs.ChargesDTO;

import java.util.List;
import java.util.UUID;

public interface iChargesService {
    ChargesDTO registerCharge(ChargeRequestDTO request);

    List<ChargesDTO> getChargesByAccusedDui(String dui);

    List<ChargeSummaryDTO> getAllChargesSummary();

    ChargesDTO findById(UUID id);

    ChargesDTO updateCharge(UUID id, ChargeUpdateDTO dto);

    void deleteCharge(UUID id);

    ChargesDTO updateChargeStatus(UUID id, ChargeStatusDTO dto);
}
