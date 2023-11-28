package com.app.eHealthBuddy.controller;

import com.app.eHealthBuddy.entity.Doctor;
import com.app.eHealthBuddy.entity.Patient;
import com.app.eHealthBuddy.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/id/{patient_id}")
    public ResponseEntity<Optional<Patient>> findByDoctorid(@PathVariable Long patient_id) {
        return new ResponseEntity<Optional<Patient>>(patientService.findById(patient_id), HttpStatus.OK);
    }
}
