package com.example.safetynetalertapp.Services;

import com.example.safetynetalertapp.model.MedicalRecords;
import com.example.safetynetalertapp.respository.MedicalRecordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private MedicalRecordsRepository medicalRecordsRepository;

    public boolean isPersonUnder18(Long personId) {
        Optional<MedicalRecords> medicalRecords = medicalRecordsRepository.findById(personId);

        if (medicalRecords.isPresent()) {
            LocalDate birthdate = medicalRecords.get().getBirthdate();
            int age = calculateAge(birthdate);


            return age < 18;
        }
        return false;
    }

        private int calculateAge (LocalDate birthdate){
            LocalDate currentDate = LocalDate.now();
            return Period.between(birthdate, currentDate).getYears();
        }
    }
