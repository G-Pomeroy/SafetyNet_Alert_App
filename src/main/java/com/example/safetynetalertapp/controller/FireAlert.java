package com.example.safetynetalertapp.controller;

import com.example.safetynetalertapp.model.FireStations;
import com.example.safetynetalertapp.model.Person;
import com.example.safetynetalertapp.respository.FireStationsRepository;
import com.example.safetynetalertapp.respository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/fire")
public class FireAlert {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping
    public ResponseEntity<Object> getFireAlert(@RequestParam("address") String address) {

        List<Person> peopleAtAddress = personRepository.findByAddress(address);

        if (peopleAtAddress.isEmpty()) {
            return new ResponseEntity<>("No people at address found / No address found.", HttpStatus.NOT_FOUND);
        }

        Set<FireStations> assignedFireStation = new HashSet<>();

        for (Person person : peopleAtAddress) {
            assignedFireStation.add(person.getFireStations());
        }

        List<Map<String, Object>> peopleDetails = new ArrayList<>();

        for (Person person : peopleAtAddress) {
            Map<String, Object> personDetails = new LinkedHashMap<>();
            personDetails.put("First Name", person.getFirstName());
            personDetails.put("Last Name", person.getLastName());
            personDetails.put("Phone Num", person.getPhoneNum());
            personDetails.put("Age", person.getMedicalRecords().calculateAge());
            personDetails.put("Medications", person.getMedicalRecords().getMedications());
            personDetails.put("Allergies", person.getMedicalRecords().getAllergies());

            peopleDetails.add(personDetails);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("Fire Station", assignedFireStation);  // You might want to refine this based on your entity structure
        response.put("People", peopleDetails);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
