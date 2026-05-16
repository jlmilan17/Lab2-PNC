package com.lab2.pnc.Repository;

import com.lab2.pnc.Model.Charges;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface iChargesRepository extends JpaRepository<Charges, UUID> {

    @Query("SELECT c.accused AS accused, " +
           "SUM(CASE WHEN c.chargeType = com.lab2.pnc.Model.Enum.ChargeType.PROCESAL THEN 1 " +
           "         WHEN c.chargeType = com.lab2.pnc.Model.Enum.ChargeType.PENAL THEN 2 " +
           "         ELSE 0 END) AS score " +
           "FROM Charges c " +
           "GROUP BY c.accused " +
           "ORDER BY score DESC")
    List<Object[]> findMostWanted(Pageable pageable);

    List<Charges> findByAccused_Dui(String dui);
}
