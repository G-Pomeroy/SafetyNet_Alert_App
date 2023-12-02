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

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/childAlert")
public class ChildAlertController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping
    public ResponseEntity<Object> getChildAlert(@RequestParam("address") String address) {


        List<Person> allPersonsAtAddress = personRepository.findByAddress(address);

        List<Person> children = allPersonsAtAddress.stream().filter(person -> person.getMedicalRecords()
                .calculateAge() <= 18).collect(Collectors.toList());

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("children", children.stream().map(person -> Map.of(
                        "firstName", person.getFirstName(),
                        "lastName", person.getLastName(),
                        "age", person.getMedicalRecords().calculateAge()
                ))
                .collect(Collectors.toList()));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
