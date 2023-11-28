package com.app.eHealthBuddy.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Entity
@Table(name="consultations")
@AllArgsConstructor
@NoArgsConstructor
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="consultation_id")
    private  Long cId;

    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate date;

    @NonNull
    private String prognosis;

    @NonNull
    private String medicines;

    @NonNull
    private  String diagnosis;

    @JsonBackReference(value = "patient_id")
    @ManyToOne
    @JoinColumn(name="patient_id",referencedColumnName = "patient_id")
    private Patient patient;

    @JsonBackReference(value = "doctor_id")
    @ManyToOne
    @JoinColumn(name="doctor_id", referencedColumnName = "doctor_id")
    private Doctor doctor;


    public Consultation(LocalDate date, @NonNull String prognosis, @NonNull String medicines, @NonNull String diagnosis, Patient patient, Doctor doctor) {

        this.date = date;
        this.prognosis = prognosis;
        this.medicines = medicines;
        this.diagnosis = diagnosis;
        this.patient = patient;
        this.doctor = doctor;
    }
}
