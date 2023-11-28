package com.app.eHealthBuddy.repository;

import com.app.eHealthBuddy.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long> {

    @Query("select d from Doctor d where lower(d.city) like %?1% and lower(d.speciality) like %?2%")
    List<Doctor> findByCityAndSpecialityIgnoreCasePartialMatch(String city, String speciality);

    Optional<Doctor> findByEmailAndPassword(String email, String password);
}
