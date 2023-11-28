package com.app.eHealthBuddy.controller;

import com.app.eHealthBuddy.entity.Doctor;
import com.app.eHealthBuddy.entity.Login;
import com.app.eHealthBuddy.entity.Patient;
import com.app.eHealthBuddy.service.DoctorService;
import com.app.eHealthBuddy.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@CrossOrigin
@Slf4j
public class HomeController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Login details){
        String email = details.getEmail();
        String password = details.getPassword();
        log.info(email+" "+password);
        Optional<Doctor> doctor  = doctorService.findByEmailAndPassword(email,password);
        Optional<Patient> patient = patientService.findByEmailAndPassword(email,password);

        if(doctor.isPresent()) return new ResponseEntity<Doctor>(doctor.get(), HttpStatus.OK);

        if(patient.isPresent()) return new ResponseEntity<Patient>(patient.get(), HttpStatus.OK);

        return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/register/doctor")
    @ResponseBody
    public String doctorRegistration(@RequestBody Doctor details){
        Doctor doctor = new Doctor(details.getDId(), details.getName(),details.getAadhar(),details.getAddress(),details.getCity(),details.getMobile(),details.getSpeciality(),new ArrayList<>());
        doctor.setEmail(details.getEmail());
        doctor.setPassword(details.getPassword());
        return doctorService.registerDoctor(doctor);
    }

    @PostMapping("/register/patient")
    @ResponseBody
    public String patientRegistration(@RequestBody Patient details){
        Patient patient = new Patient(details.getPId(), details.getName(),details.getAadhar(), details.getDob(),details.getAddress(),details.getCity(),details.getMobile(),new ArrayList<>());
        patient.setEmail(details.getEmail());
        patient.setPassword(details.getPassword());
        return patientService.registerPatient(patient);
    }
}
