package com.lab2.pnc.Service.ServiceImp;

import com.lab2.pnc.Model.Address;
import com.lab2.pnc.Model.DTOs.AddressDTO;
import com.lab2.pnc.Model.DTOs.PersonDTO;
import com.lab2.pnc.Model.DTOs.PoliceOfficerDTO;
import com.lab2.pnc.Model.DTOs.PoliceStationDTO;
import com.lab2.pnc.Model.Person;
import com.lab2.pnc.Model.PoliceOfficer;
import com.lab2.pnc.Model.PoliceStation;
import com.lab2.pnc.Repository.iPoliceStationRepository;
import com.lab2.pnc.Service.iPoliceStationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PoliceStationServiceImp implements iPoliceStationService {
    private final iPoliceStationRepository policeStationRepository;


    @Override
    @Transactional(readOnly = true)
    public List<PoliceStationDTO> findAll() {
        return policeStationRepository.findAll().stream().map(this::toDTO).toList();
    }

    private AddressDTO toAddressDTO(Address address) {
        return AddressDTO.builder()
                .department(address.getDepartment().getName())
                .zone(address.getDepartment().getZone().name())
                .street(address.getStreet())
                .municipality(address.getMunicipality())
                .neighborhood(address.getNeighborhood())
                .build();
    }

    private PersonDTO toPersonDTO(Person person) {
        PersonDTO dto = new PersonDTO();
        dto.setName(person.getName());
        dto.setDui(person.getDui());
        dto.setPhoneNumber(person.getPhoneNumber());
        dto.setAddressDTO(toAddressDTO(person.getAddress()));
        return dto;
    }

    private PoliceOfficerDTO toPoliceOfficerDTO(PoliceOfficer officer) {
        PoliceOfficerDTO dto = new PoliceOfficerDTO();
        dto.setPersonDTO(toPersonDTO(officer.getPerson()));
        dto.setCode(officer.getCodeNumber());
        dto.setBadge(officer.getBadge());
        dto.setPoliceStationDTO(toPoliceStationDTOLite(officer.getPoliceStation()));
        return dto;
    }

    private PoliceStationDTO toPoliceStationDTOLite(PoliceStation station) {
        PoliceStationDTO dto = new PoliceStationDTO();
        dto.setName(station.getName());
        dto.setAddressDTO(toAddressDTO(station.getAddress()));
        return dto;
    }


    private PoliceStationDTO toDTO(PoliceStation policeStation) {
        PoliceStationDTO dto = new PoliceStationDTO();
        dto.setName(policeStation.getName());
        dto.setAddressDTO(toAddressDTO(policeStation.getAddress()));
        if (policeStation.getDirector() != null) {
            dto.setDirector(toPoliceOfficerDTO(policeStation.getDirector()));
        }
        return dto;
    }
}
