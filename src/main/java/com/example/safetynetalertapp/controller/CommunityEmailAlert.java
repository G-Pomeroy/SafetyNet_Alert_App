package com.example.safetynetalertapp.controller;

import com.example.safetynetalertapp.model.Person;
import com.example.safetynetalertapp.respository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/communityEmail")
public class CommunityEmailAlert {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping
    public ResponseEntity<Object> getAllEmail(@RequestParam ("city")String city){

        List<Person> allCityEmailAddresses = personRepository.findByCity(city);

        if (allCityEmailAddresses.isEmpty()) {
            return new ResponseEntity<>("No people / Email addresses found at this location.",
                    HttpStatus.NOT_FOUND);
        }

        List<String> emailAddresses = allCityEmailAddresses.stream()
                .map(Person::getEmail)
                .collect(Collectors.toList());

        return new ResponseEntity<>(emailAddresses, HttpStatus.OK);

    }
}
