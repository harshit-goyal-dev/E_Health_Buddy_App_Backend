package com.app.eHealthBuddy.repository;

import com.app.eHealthBuddy.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {

    Optional<Patient> findByEmailAndPassword(String email, String password);
}
