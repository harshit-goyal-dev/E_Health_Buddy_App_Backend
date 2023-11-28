package com.app.eHealthBuddy.controller;

import com.app.eHealthBuddy.dto.ConsultationFormDTO;
import com.app.eHealthBuddy.dto.DoctorConsultationDTO;
import com.app.eHealthBuddy.dto.PatientConsultationDTO;
import com.app.eHealthBuddy.service.ConsultationService;
import com.app.eHealthBuddy.service.DoctorService;
import com.app.eHealthBuddy.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/consultations")
public class ConsultationController {

    @Autowired
    private ConsultationService consultationService;


    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    @GetMapping("/patient/patient_id")
    public ResponseEntity<DoctorConsultationDTO> doctorHistory(@PathVariable Long doctor_id){
        DoctorConsultationDTO doctorConsultationDTO = doctorService.findByIdGroupByPatient(doctor_id);
        if(doctorConsultationDTO.getDoctor() != null)
            return new ResponseEntity<DoctorConsultationDTO>(doctorConsultationDTO, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/doctor/doctor_id")
    public ResponseEntity<PatientConsultationDTO> patientHistory(@PathVariable Long patient_id){
        PatientConsultationDTO patientConsultationDTO = patientService.findByIdGroupByDoctor(patient_id);
        if(patientConsultationDTO.getPatient() != null)
            return new ResponseEntity<PatientConsultationDTO>(patientConsultationDTO, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/form")
    @ResponseBody
    public String consultPatient(@RequestBody ConsultationFormDTO consultationForm){
        return consultationService.saveConsultation(consultationForm);
    }
}
