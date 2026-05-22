package com.lab2.pnc.Service;

import com.lab2.pnc.Model.DTOs.PoliceStationDTO;

import java.util.List;

public interface iPoliceStationService {
    List<PoliceStationDTO> findAll();
}
