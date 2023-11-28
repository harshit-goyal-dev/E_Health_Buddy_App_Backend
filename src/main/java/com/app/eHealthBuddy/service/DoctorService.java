package com.app.eHealthBuddy.service;

import com.app.eHealthBuddy.dto.DoctorConsultationDTO;
import com.app.eHealthBuddy.entity.Consultation;
import com.app.eHealthBuddy.entity.Doctor;
import com.app.eHealthBuddy.entity.Patient;
import com.app.eHealthBuddy.pojos.DoctorConsultation;
import com.app.eHealthBuddy.repository.ConsultationRepository;
import com.app.eHealthBuddy.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    ConsultationRepository consultationRepository;

    public List<Doctor> findByCityAndSpeciality(String city, String speciality){
        return doctorRepository.findByCityAndSpecialityIgnoreCasePartialMatch(city.trim().toLowerCase(), speciality.trim().toLowerCase());
    }

    public Optional<Doctor> findById(Long doctor_id){
        return doctorRepository.findById(doctor_id);
    }

    public DoctorConsultationDTO findByIdGroupByPatient(Long doctor_id){
        Optional<Doctor> d = doctorRepository.findById(doctor_id);
        Doctor doctor = d.isPresent() ? d.get():null;

        List<Consultation> consultations = consultationRepository.findByDoctorId(doctor_id);

        HashMap<Patient, List<Consultation>> map = new HashMap();
        consultations.stream().forEach( c ->{
            Patient patient = c.getPatient();
            if(map.containsKey(patient)){
                List<Consultation> consultationList = map.get(patient);
                consultationList.add(c);
                map.put(patient,consultationList);
            }else{
                List<Consultation> consultationList = new ArrayList<>();
                consultationList.add(c);
                //why put if absent
                map.putIfAbsent(patient,consultationList);
            }
                });

        List<DoctorConsultation> doctorConsultationList = new ArrayList<>();
        for(Map.Entry<Patient, List<Consultation>> entry : map.entrySet()){
            DoctorConsultation doctorConsultation = new DoctorConsultation(entry.getKey(), entry.getValue());
            doctorConsultationList.add(doctorConsultation);
        }

        return new DoctorConsultationDTO(doctor, doctorConsultationList);
    }

    public Optional<Doctor> findByEmailAndPassword(String email, String password){
        return doctorRepository.findByEmailAndPassword(email,password);
    }

    public String registerDoctor(Doctor doctor){
        if(doctorRepository.findById((long) doctor.getDId()).isPresent()){
            return "Doctor already Exists";
        }else{
            try{
                doctorRepository.save(doctor);
                return "Doctor registered successfully";
            }catch(Exception exception){
                return "Doctor registration failed";
            }
        }
    }

}
