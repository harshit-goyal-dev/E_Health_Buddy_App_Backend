package com.app.eHealthBuddy;

import com.app.eHealthBuddy.entity.Consultation;
import com.app.eHealthBuddy.entity.Doctor;
import com.app.eHealthBuddy.entity.Patient;
import com.app.eHealthBuddy.repository.ConsultationRepository;
import com.app.eHealthBuddy.repository.DoctorRepository;
import com.app.eHealthBuddy.repository.PatientRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.ArrayList;

@SpringBootApplication
public class EHealthBuddyApplication {

	@Autowired
	PatientRepository patientRepository;

	@Autowired
	DoctorRepository doctorRepository;

	@Autowired
	ConsultationRepository consultationRepository;

	@PostConstruct
	public void initDB(){

		//Patient patient = new Patient(12L,"abc",123456789L, LocalDate.parse("1990-02-23"),"pqqr","city",Long.valueOf(98362782828L), new ArrayList<>());
		Patient patient = new Patient(Long.valueOf(12), "Satyam Srivastava", Long.valueOf(111122223333L), LocalDate.parse("1991-02-18"),"135, Kanshi Ram Shah Marg, Block GH 14, Paschim Vihar","Delhi", Long.valueOf(9990843434L), new ArrayList<>());
		Doctor doctor1 = new Doctor(Long.valueOf(100), "Ajay Sharma", Long.valueOf(222233334444L), "F-87, Pocket F, Phase 1, Ashok Vihar", "Delhi", Long.valueOf(9311733366L), "Cardiologist", new ArrayList<>());
		Doctor doctor2 = new Doctor(Long.valueOf(200), "Manoj Jain", Long.valueOf(333344445555L), "B-362, Near to ICICI Bank, Block B, Meera Bagh, Paschim Vihar", "Delhi", Long.valueOf(9837199390L), "Dermatologist", new ArrayList<>());
		patient.setEmail("satyam@gmail.com");
		patient.setPassword("satyam");
		doctor1.setEmail("ajay@gmail.com");
		doctor1.setPassword("ajay");
		doctor2.setEmail("manoj@gmail.com");
		doctor2.setPassword("manoj");
		patientRepository.save(patient);
		doctorRepository.save(doctor1);
		doctorRepository.save(doctor2);
		Consultation consultation1 = new Consultation(LocalDate.parse("2021-08-19"), "Heart failure", "Lescol XL, Ranolazine, Nitroglycerin", "Coronary Artery Disease", patient, doctor1);
		Consultation consultation2 = new Consultation(LocalDate.parse("2021-09-02"), "Toxic epidermal necrolysis (TEN)", "Dexamethasone, Ibuprofen", "Stevens-Johnson Syndrome", patient, doctor2);
		consultationRepository.save(consultation1);
		consultationRepository.save(consultation2);
	}
	public static void main(String[] args) {
		SpringApplication.run(EHealthBuddyApplication.class, args);
	}

}
