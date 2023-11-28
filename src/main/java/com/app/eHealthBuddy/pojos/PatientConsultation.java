package com.app.eHealthBuddy.pojos;

import com.app.eHealthBuddy.entity.Consultation;
import com.app.eHealthBuddy.entity.Doctor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.print.Doc;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientConsultation {
    Doctor doctor;
    List<Consultation> consultations;
}
