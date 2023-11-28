package com.app.eHealthBuddy.dto;

import com.app.eHealthBuddy.entity.Patient;
import com.app.eHealthBuddy.pojos.PatientConsultation;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PatientConsultationDTO {
    Patient patient;
    List<PatientConsultation> patient_consultations;
}
