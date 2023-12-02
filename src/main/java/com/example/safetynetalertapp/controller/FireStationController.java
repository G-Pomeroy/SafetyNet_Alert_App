package com.example.safetynetalertapp.controller;

import com.example.safetynetalertapp.model.FireStations;
import com.example.safetynetalertapp.model.Person;
import com.example.safetynetalertapp.respository.FireStationsRepository;
import com.example.safetynetalertapp.respository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/firestation")
public class FireStationController {

    @Autowired
    private FireStationsRepository fireStationsRepository;

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/allFireStations")
    public ResponseEntity<List<FireStations>> getAllFireStations(){

        List<FireStations> fireStations = new ArrayList<FireStations>(fireStationsRepository.findAll());

        return new ResponseEntity<>(fireStations, HttpStatus.OK);
    }

    @GetMapping
    //Entering the station ID number will fetch a list of all people under that station
    public ResponseEntity<Map<String,Object>> getPeopleByStationNumber(@RequestParam("fireStationId") Long fireStationId){
        List<Person> people = fireStationsRepository.findPeopleByFireStationId(fireStationId);

        List<Person> adults = people.stream()
                .filter(person -> person.getMedicalRecords().calculateAge() > 18)
                .collect(Collectors.toList());

        List<Person> children = people.stream()
                .filter(person -> person.getMedicalRecords().calculateAge() <= 18)
                .collect(Collectors.toList());

        //Print adult info
        Map<String, Object> response = new HashMap<>();
        List<Map<String, String>> adultsInfo = adults.stream().map(person -> {
            Map<String, String> personDetails = new HashMap<>();
            personDetails.put("First Name", person.getFirstName());
            personDetails.put("Last Name", person.getLastName());
            personDetails.put("Address", person.getAddress());
            personDetails.put("Phone Number", person.getPhoneNum());
            return personDetails;
        }).collect(Collectors.toList());

        //Print children info
        List<Map<String, String>> childrenInfo = children.stream().map(person -> {
            Map<String, String> personDetails = new HashMap<>();
            personDetails.put("First Name", person.getFirstName());
            personDetails.put("Last Name", person.getLastName());
            personDetails.put("Address", person.getAddress());
            personDetails.put("Phone Number", person.getPhoneNum());
            return personDetails;
        }).collect(Collectors.toList());

        response.put("Adults Count", adults.size());
        response.put("Adults", adultsInfo);
        response.put("Children Count", children.size());
        response.put("Children", childrenInfo);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping("/addstation")
    public ResponseEntity<FireStations> addStation(@RequestBody FireStations fireStations){
        FireStations savedStation = fireStationsRepository.save(fireStations);
        return new ResponseEntity<>(savedStation, HttpStatus.CREATED);
    }





}
