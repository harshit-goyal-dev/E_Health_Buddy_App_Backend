package com.app.eHealthBuddy.controller;

import com.app.eHealthBuddy.entity.Doctor;
import com.app.eHealthBuddy.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/city/{city}")
    public ResponseEntity<List<Doctor>> findByCity(@PathVariable String city, @RequestParam String speciality){
        return new ResponseEntity<List<Doctor>>(doctorService.findByCityAndSpeciality(city,speciality), HttpStatus.OK);
    }

    @GetMapping("/id/{doctor_id}")
    public ResponseEntity<Optional<Doctor>> findByDoctorid(@PathVariable Long doctor_id){
        return new ResponseEntity<Optional<Doctor>>(doctorService.findById(doctor_id), HttpStatus.OK);
    }
}
