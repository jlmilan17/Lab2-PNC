package com.lab2.pnc.Service.ServiceImp;

import com.lab2.pnc.HandlerException.ChargeNotFoundException;
import com.lab2.pnc.HandlerException.PersonNotFoundException;
import com.lab2.pnc.HandlerException.PoliceOfficerNotFoundException;
import com.lab2.pnc.HandlerException.PoliceStationNotFoundException;
import com.lab2.pnc.HandlerException.SameAccuserAccusedException;
import com.lab2.pnc.Model.Address;
import com.lab2.pnc.Model.Charges;
import com.lab2.pnc.Model.DTOs.AddressDTO;
import com.lab2.pnc.Model.DTOs.ChargeRequestDTO;
import com.lab2.pnc.Model.DTOs.ChargeSummaryDTO;
import com.lab2.pnc.Model.DTOs.ChargeStatusDTO;
import com.lab2.pnc.Model.DTOs.ChargeUpdateDTO;
import com.lab2.pnc.Model.DTOs.ChargesDTO;
import com.lab2.pnc.Model.DTOs.PersonDTO;
import com.lab2.pnc.Model.DTOs.PersonSummaryDTO;
import com.lab2.pnc.Model.DTOs.PoliceOfficerDTO;
import com.lab2.pnc.Model.DTOs.PoliceStationDTO;
import com.lab2.pnc.Model.Enum.ChargeStatus;
import com.lab2.pnc.Model.Person;
import com.lab2.pnc.Model.PoliceOfficer;
import com.lab2.pnc.Model.PoliceStation;
import com.lab2.pnc.Repository.iChargesRepository;
import com.lab2.pnc.Repository.iPersonRepository;
import com.lab2.pnc.Repository.iPoliceOfficerRepository;
import com.lab2.pnc.Repository.iPoliceStationRepository;
import com.lab2.pnc.Service.iChargesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChargesServiceImp implements iChargesService {
    private final iChargesRepository chargesRepository;
    private final iPersonRepository personRepository;
    private final iPoliceOfficerRepository policeOfficerRepository;
    private final iPoliceStationRepository policeStationRepository;

    @Override
    @Transactional
    public ChargesDTO registerCharge(ChargeRequestDTO request) {
        if (request.getAccuserDui().equals(request.getAccusedDui())) {
            throw new SameAccuserAccusedException();
        }

        Person accuser = personRepository.findPersonByDui(request.getAccuserDui());
        if (accuser == null) throw new PersonNotFoundException(request.getAccuserDui());

        Person accused = personRepository.findPersonByDui(request.getAccusedDui());
        if (accused == null) throw new PersonNotFoundException(request.getAccusedDui());

        PoliceOfficer officer = policeOfficerRepository.findByCodeNumber(request.getOfficerCode());
        if (officer == null) throw new PoliceOfficerNotFoundException(request.getOfficerCode());

        PoliceStation station = policeStationRepository.findByName(request.getPoliceStationName());
        if (station == null) throw new PoliceStationNotFoundException(request.getPoliceStationName());

        Charges charge = Charges.builder()
                .date(request.getDate())
                .chargeType(request.getChargeType())
                .status(ChargeStatus.ACTIVA)
                .accuser(accuser)
                .accused(accused)
                .registeredBy(officer)
                .policeStation(station)
                .description(request.getDescription())
                .build();

        Charges saved = chargesRepository.save(charge);
        return toChargesDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ChargesDTO> getChargesByAccusedDui(String dui) {
        return chargesRepository.findByAccused_Dui(dui).stream()
                .map(this::toChargesDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ChargeSummaryDTO> getAllChargesSummary() {
        return chargesRepository.findAll().stream()
                .map(this::toChargeSummary)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ChargesDTO findById(UUID id) {
        Charges charge = chargesRepository.findById(id)
                .orElseThrow(() -> new ChargeNotFoundException(id));
        return toChargesDTO(charge);
    }

    @Override
    @Transactional
    public ChargesDTO updateCharge(UUID id, ChargeUpdateDTO dto) {
        Charges charge = chargesRepository.findById(id)
                .orElseThrow(() -> new ChargeNotFoundException(id));

        if (dto.getDate() != null) charge.setDate(dto.getDate());
        if (dto.getChargeType() != null) charge.setChargeType(dto.getChargeType());
        if (dto.getDescription() != null) charge.setDescription(dto.getDescription());

        return toChargesDTO(chargesRepository.save(charge));
    }

    @Override
    @Transactional
    public void deleteCharge(UUID id) {
        if (!chargesRepository.existsById(id)) {
            throw new ChargeNotFoundException(id);
        }
        chargesRepository.deleteById(id);
    }

    @Override
    @Transactional
    public ChargesDTO updateChargeStatus(UUID id, ChargeStatusDTO dto) {
        Charges charge = chargesRepository.findById(id)
                .orElseThrow(() -> new ChargeNotFoundException(id));

        charge.setStatus(dto.getStatus());
        return toChargesDTO(chargesRepository.save(charge));
    }

    private ChargeSummaryDTO toChargeSummary(Charges charge) {
        PersonSummaryDTO accuser = new PersonSummaryDTO();
        accuser.setName(charge.getAccuser().getName());
        accuser.setDui(charge.getAccuser().getDui());

        ChargeSummaryDTO dto = new ChargeSummaryDTO();
        dto.setId(charge.getChargesUuid());
        dto.setChargeType(charge.getChargeType());
        dto.setStatus(charge.getStatus());
        dto.setAccuser(accuser);
        dto.setOfficerName(charge.getRegisteredBy().getPerson().getName());
        return dto;
    }

    private ChargesDTO toChargesDTO(Charges entity) {
        ChargesDTO dto = new ChargesDTO();
        dto.setDate(entity.getDate());
        dto.setChargeType(entity.getChargeType());
        dto.setStatus(entity.getStatus());
        dto.setDescription(entity.getDescription());
        dto.setAccuser(toPersonDTO(entity.getAccuser()));
        dto.setAccused(toPersonDTO(entity.getAccused()));
        dto.setRegisteredBy(toPoliceOfficerDTO(entity.getRegisteredBy()));
        dto.setPoliceStation(toPoliceStationDTO(entity.getPoliceStation()));
        return dto;
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

    private PoliceStationDTO toPoliceStationDTO(PoliceStation station) {
        PoliceStationDTO dto = toPoliceStationDTOLite(station);
        if (station.getDirector() != null) {
            dto.setDirector(toPoliceOfficerDTO(station.getDirector()));
        }
        return dto;
    }

    private PoliceStationDTO toPoliceStationDTOLite(PoliceStation station) {
        PoliceStationDTO dto = new PoliceStationDTO();
        dto.setName(station.getName());
        dto.setAddressDTO(toAddressDTO(station.getAddress()));
        return dto;
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
}
