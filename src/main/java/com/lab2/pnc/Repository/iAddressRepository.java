package com.lab2.pnc.Repository;

import com.lab2.pnc.Model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface iAddressRepository extends JpaRepository<Address, UUID> {
}
