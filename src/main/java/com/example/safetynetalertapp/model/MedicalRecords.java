package com.example.safetynetalertapp.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;


@Entity
@Table(name = "medicalRecords")
public class MedicalRecords {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long medicalId;
    @Column(name="birthdate")
    private LocalDate birthdate;
    @ElementCollection
    private List<String> medications;
    @ElementCollection
    private List<String> allergies;


    //Constructors
    public MedicalRecords(){
    }

    public MedicalRecords(Long medicalId, LocalDate birthdate, List<String> medications, List<String> allergies) {
        this.medicalId = medicalId;
        this.birthdate = birthdate;
        this.medications = medications;
        this.allergies = allergies;
    }

    //Getters & Setters
    public Long getMedicalId() {
        return medicalId;
    }

    public void setMedicalId(Long medicalId) {
        this.medicalId = medicalId;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public List<String> getMedications() {
        return medications;
    }

    public void setMedications(List<String> medications) {
        this.medications = medications;
    }

    public List<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }

    @Override
    public String toString() {
        return "MedicalRecord{" +
                "medicalId=" + medicalId +
                ", birthdate=" + birthdate +
                ", medications=" + medications +
                ", allergies=" + allergies +
                '}';
    }

    public int calculateAge(){
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthdate,currentDate).getYears();
    }



}
