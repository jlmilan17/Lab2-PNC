package com.lab2.pnc.Repository;

import com.lab2.pnc.Model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface iPersonRepository extends JpaRepository<Person, UUID> {

    Person findPersonByDui(String dui);

    @Query("SELECT DISTINCT c.accused FROM Charges c")
    List<Person> findAllWithCharges();
}
