package com.example.safetynetalertapp.controller;

import com.example.safetynetalertapp.model.Person;
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
@RequestMapping("/phoneAlert")
public class PhoneAlert {

    @Autowired
    private PersonRepository personRepository;


    @GetMapping
    public ResponseEntity<Object> getPhoneAlert(@RequestParam("fireStationId") Long fireStationId) {

        List<Person> people = personRepository.findByFireStations_FireStationId(fireStationId);

        List<Map<String, String>> phoneAlertList = new ArrayList<>();

        for (Person person : people) {
            Map<String, String> phoneAlert = new LinkedHashMap<>();
            phoneAlert.put("firstName", person.getFirstName());
            phoneAlert.put("lastName", person.getLastName());
            phoneAlert.put("phoneNum", person.getPhoneNum());
            phoneAlertList.add(phoneAlert);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("people", phoneAlertList);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
