package com.machlou.patientmanager.repositories;

import com.machlou.patientmanager.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
//    @Query("SELECT u FROM User u WHERE u.status = 1")
    @Query("SELECT p FROM Patient p WHERE p.firstName like %:key% or p.lastName like %:key% or p.email like %:key%")
    public List<Patient> getByKeyword(@Param("key") String key);
//    public Page<Patient> getByKeyword(@Param("key") String key, PageRequest of);
}
