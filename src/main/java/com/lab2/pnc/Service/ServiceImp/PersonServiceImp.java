package com.lab2.pnc.Service.ServiceImp;

import com.lab2.pnc.HandlerException.DuplicateDuiException;
import com.lab2.pnc.HandlerException.PersonNotFoundException;
import com.lab2.pnc.Model.Address;
import com.lab2.pnc.Model.DTOs.AddressDTO;
import com.lab2.pnc.Model.DTOs.MostWantedDTO;
import com.lab2.pnc.Model.DTOs.PersonChargesDTO;
import com.lab2.pnc.Model.DTOs.PersonDTO;
import com.lab2.pnc.Model.Person;
import com.lab2.pnc.Repository.iChargesRepository;
import com.lab2.pnc.Repository.iPersonRepository;
import com.lab2.pnc.Service.iChargesService;
import com.lab2.pnc.Service.iPersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonServiceImp implements iPersonService {
    private final iPersonRepository personRepository;
    private final iChargesRepository chargesRepository;
    private final iChargesService chargesService;

    @Override
    public PersonDTO registerPerson(PersonDTO personDTO) {
        if (personRepository.findPersonByDui(personDTO.getDui()) != null) {
            throw new DuplicateDuiException(personDTO.getDui());
        }

        Person personToDB = Person.builder()
                .name(personDTO.getName())
                .dui(personDTO.getDui())
                .address(toAddressEntity(personDTO.getAddressDTO()))
                .phoneNumber(personDTO.getPhoneNumber())
                .build();

        Person saved = personRepository.save(personToDB);
        return toDTO(saved);
    }

    @Override
    public List<PersonDTO> findAllWithCharges() {
        return personRepository.findAllWithCharges().stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public PersonChargesDTO findChargesOf(String dui) {
        Person person = personRepository.findPersonByDui(dui);
        if (person == null) throw new PersonNotFoundException(dui);

        PersonChargesDTO dto = new PersonChargesDTO();
        dto.setPerson(toDTO(person));
        dto.setCharges(chargesService.getChargesByAccusedDui(dui));
        return dto;
    }

    @Override
    public List<MostWantedDTO> findMostWanted() {
        return chargesRepository.findMostWanted(PageRequest.of(0, 3)).stream()
                .map(row -> {
                    MostWantedDTO dto = new MostWantedDTO();
                    dto.setPerson(toDTO((Person) row[0]));
                    dto.setScore(((Number) row[1]).intValue());
                    return dto;
                })
                .toList();
    }

    @Override
    public List<PersonDTO> findAll() {
        return personRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public PersonDTO findByDui(String dui) {
        Person person = personRepository.findPersonByDui(dui);
        if (person == null) throw new PersonNotFoundException(dui);
        return toDTO(person);
    }

    @Override
    public PersonDTO updatePerson(String dui, PersonDTO personDTO) {
        Person person = personRepository.findPersonByDui(dui);
        if (person == null) throw new PersonNotFoundException(dui);

        person.setName(personDTO.getName());
        person.setPhoneNumber(personDTO.getPhoneNumber());
        person.setAddress(toAddressEntity(personDTO.getAddressDTO()));

        return toDTO(personRepository.save(person));
    }

    @Override
    public void deletePerson(String dui) {
        Person person = personRepository.findPersonByDui(dui);
        if (person == null) throw new PersonNotFoundException(dui);
        chargesRepository.deleteAll(chargesRepository.findByAccused_Dui(dui));
        chargesRepository.deleteAll(chargesRepository.findByAccuser_Dui(dui));
        personRepository.delete(person);
    }

    private Address toAddressEntity(AddressDTO dto) {
        return Address.builder()
                .department(dto.getDepartment())
                .street(dto.getStreet())
                .municipality(dto.getMunicipality())
                .neighborhood(dto.getNeighborhood())
                .build();
    }

    private AddressDTO toAddressDTO(Address address) {
        return AddressDTO.builder()
                .department(address.getDepartment())
                .street(address.getStreet())
                .municipality(address.getMunicipality())
                .neighborhood(address.getNeighborhood())
                .build();
    }

    private PersonDTO toDTO(Person person) {
        PersonDTO dto = new PersonDTO();
        dto.setName(person.getName());
        dto.setDui(person.getDui());
        dto.setPhoneNumber(person.getPhoneNumber());
        dto.setAddressDTO(toAddressDTO(person.getAddress()));
        return dto;
    }
}
