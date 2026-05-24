package com.lab2.pnc.Repository;

import com.lab2.pnc.Model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface iDepartmentRepository extends JpaRepository<Department, UUID> {
    Department findByName(String name);
}
