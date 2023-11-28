package com.app.eHealthBuddy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConsultationFormDTO {
    private Long patient_id;
    private Long doctor_id;
    private String diagnosis;
    private String prognosis;
    private String medicines;
}
