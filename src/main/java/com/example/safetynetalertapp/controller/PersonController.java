package com.example.safetynetalertapp.controller;

import com.example.safetynetalertapp.model.Person;
import com.example.safetynetalertapp.model.FireStations;
import com.example.safetynetalertapp.respository.FireStationsRepository;
import com.example.safetynetalertapp.respository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/personInfo")
public class PersonController {

    Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private FireStationsRepository fireStationsRepository;

    @GetMapping("/allpersons")
    public ResponseEntity<List<Person>> getAllPersons(){

        List<Person> persons = new ArrayList<Person>(personRepository.findAll());

        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Person>> getPerson(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName
    ) {
        List<Person> matchingPersons = personRepository.findByFirstNameAndLastName(firstName, lastName);

        return new ResponseEntity<>(matchingPersons, HttpStatus.OK);
    }

    @PostMapping("/addperson")
    public ResponseEntity<Person> addPerson(@RequestBody Person person){
        Person savedPerson = personRepository.save(person);

        FireStations fireStations = fireStationsRepository.findById(person.getFireStations().getFireStationId())
                .orElseThrow(() -> new EntityNotFoundException("Fire Station not found with id: " +
                        person.getFireStations().getFireStationId()));

        savedPerson.setFireStations(fireStations);
        personRepository.save(savedPerson);

        return new ResponseEntity<>(savedPerson, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteAllPersons")
    public ResponseEntity<String> deleteAllPersons(){
        try {
            personRepository.deleteAll();
            return new ResponseEntity<>("All persons deleted successfully!",HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("Failed to delete all Persons Records.",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
