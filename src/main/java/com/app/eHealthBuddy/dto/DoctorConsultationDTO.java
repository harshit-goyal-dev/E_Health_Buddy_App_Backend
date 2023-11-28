package com.app.eHealthBuddy.dto;

import com.app.eHealthBuddy.entity.Doctor;
import com.app.eHealthBuddy.pojos.DoctorConsultation;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DoctorConsultationDTO {
    Doctor doctor;
    List<DoctorConsultation> doctor_consulations;
}
