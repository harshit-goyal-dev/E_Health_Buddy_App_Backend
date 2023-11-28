package com.app.eHealthBuddy.pojos;

import com.app.eHealthBuddy.entity.Consultation;
import com.app.eHealthBuddy.entity.Patient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorConsultation {
    Patient patient;
    List<Consultation> consultations;
}
