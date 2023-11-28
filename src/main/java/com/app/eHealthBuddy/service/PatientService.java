package com.app.eHealthBuddy.service;

import com.app.eHealthBuddy.dto.PatientConsultationDTO;
import com.app.eHealthBuddy.entity.Consultation;
import com.app.eHealthBuddy.entity.Doctor;
import com.app.eHealthBuddy.entity.Patient;
import com.app.eHealthBuddy.pojos.PatientConsultation;
import com.app.eHealthBuddy.repository.ConsultationRepository;
import com.app.eHealthBuddy.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PatientService {
    @Autowired
    PatientRepository patientRepository;

    @Autowired
    ConsultationRepository consultationRepository;

    public Optional<Patient> findById(Long patient_id){
        return patientRepository.findById(patient_id);
    }

    public PatientConsultationDTO findByIdGroupByDoctor(Long patient_id){
        Optional<Patient> p = patientRepository.findById(patient_id);
        Patient patient = p.isPresent() ? p.get():null;

        List<Consultation> consultations = consultationRepository.findByPatientId(patient_id);

        HashMap<Doctor, List<Consultation>> map = new HashMap();
        consultations.stream().forEach( c ->{
            Doctor doctor = c.getDoctor();
            if(map.containsKey(doctor)){
                List<Consultation> consultationList = map.get(doctor);
                consultationList.add(c);
                map.put(doctor,consultationList);
            }else{
                List<Consultation> consultationList = new ArrayList<>();
                consultationList.add(c);
                //why put if absent
                map.putIfAbsent(doctor,consultationList);
            }
        });

        List<PatientConsultation> patientConsultationList = new ArrayList<>();
        for(Map.Entry<Doctor, List<Consultation>> entry : map.entrySet()){
            PatientConsultation patientConsultation = new PatientConsultation(entry.getKey(), entry.getValue());
            patientConsultationList.add(patientConsultation);
        }

        return new PatientConsultationDTO(patient, patientConsultationList);
    }

    public Optional<Patient> findByEmailAndPassword(String email, String password){
        return patientRepository.findByEmailAndPassword(email,password);
    }

    public String registerPatient(Patient patient){
        if(patientRepository.findById((long) patient.getPId()).isPresent()){
            return "Patient already Exists";
        }else{
            try{
                patientRepository.save(patient);
                return "Patient registered successfully";
            }catch(Exception exception){
                return "Patient registration failed";
            }
        }
    }
}
