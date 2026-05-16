package com.lab2.pnc.Repository;

import com.lab2.pnc.Model.PoliceOfficer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface iPoliceOfficerRepository extends JpaRepository<PoliceOfficer, UUID> {

    PoliceOfficer findByCodeNumber(String codeNumber);
}
