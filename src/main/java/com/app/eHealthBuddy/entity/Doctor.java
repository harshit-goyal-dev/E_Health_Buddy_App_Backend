package com.app.eHealthBuddy.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="doctors")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Doctor extends Login{

    @Id
    @Column(name = "doctor_id", unique = true)
    private Long dId;

    private String name;

    @Column(unique = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long aadhar;

    private String address;

    private  String city;

    @Column(unique = true)
    private Long mobile;

    private  String speciality;

    @JsonManagedReference(value="doctor_id")
    @OneToMany(mappedBy = "doctor")
    private List<Consultation> consultations = new ArrayList<>();

    public Doctor(String email, String password, Long dId, String name, Long aadhar, String address, String city, Long mobile, String speciality) {
        super(email, password);
        this.dId = dId;
        this.name = name;
        this.aadhar = aadhar;
        this.address = address;
        this.city = city;
        this.mobile = mobile;
        this.speciality = speciality;
    }
}
