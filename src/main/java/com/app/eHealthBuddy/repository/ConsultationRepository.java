package com.app.eHealthBuddy.repository;

import com.app.eHealthBuddy.entity.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation,Long> {

    @Query("select c from Consultation c where c.patient.pId=?1")
    List<Consultation> findByPatientId(Long patient_id);

    @Query("select c from Consultation c where c.doctor.dId=?1")
    List<Consultation> findByDoctorId(Long doctor_id);
}
