package com.app.eHealthBuddy.service;

import com.app.eHealthBuddy.dto.ConsultationFormDTO;
import com.app.eHealthBuddy.dto.PatientConsultationDTO;
import com.app.eHealthBuddy.entity.Consultation;
import com.app.eHealthBuddy.entity.Doctor;
import com.app.eHealthBuddy.entity.Patient;
import com.app.eHealthBuddy.repository.ConsultationRepository;
import com.app.eHealthBuddy.repository.DoctorRepository;
import com.app.eHealthBuddy.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultationService {

    @Autowired
    private ConsultationRepository consultationRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;


    public List<Consultation> findByPatientId(Long patient_id){
        return consultationRepository.findByPatientId(patient_id);
    }

    public String saveConsultation(ConsultationFormDTO consultationForm){
        Long patient_id = consultationForm.getPatient_id();
        Long doctor_id = consultationForm.getDoctor_id();
        String prognosis = consultationForm.getPrognosis();
        String medicines = consultationForm.getMedicines();
        String diagnosis = consultationForm.getDiagnosis();

        Optional<Doctor> doctor = doctorRepository.findById(doctor_id);
        Optional<Patient> patient = patientRepository.findById(patient_id);
        if(doctor.isPresent() && patient.isPresent()){
            Consultation consultation = new Consultation(LocalDate.now(),prognosis,medicines,diagnosis,patient.get(),doctor.get());
            consultationRepository.save(consultation);
            return "Patient consulted successfully";
        }
        return "Patient Consultation failed";
    }
}
