package com.lab2.pnc.Repository;

import com.lab2.pnc.Model.PoliceStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface iPoliceStationRepository extends JpaRepository<PoliceStation, UUID> {

    PoliceStation findByName(String name);
}
