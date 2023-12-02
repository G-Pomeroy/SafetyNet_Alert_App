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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/flood")
public class FloodAlert {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private FireStationsRepository fireStationsRepository;

    @GetMapping
    public ResponseEntity<Object> getFloodAlert(@RequestParam("fireStationId") List<Long> fireStationIds) {

        List<FireStations> fireStations = fireStationsRepository.findAllByFireStationIdIn(fireStationIds);

        Map<String, List<Map<String, Object>>> allStationsMap = new HashMap<>();

        for (FireStations fireStation : fireStations) {
            Set<String> assignedAddresses = personRepository.findByFireStations_FireStationId(fireStation.getFireStationId())
                    .stream()
                    .map(Person::getAddress)
                    .collect(Collectors.toSet());

            List<Map<String, Object>> allPersonsList = new ArrayList<>();

            for (String address : assignedAddresses) {
                List<Map<String, String>> peopleDetailsList = personRepository.findByAddress(address)
                        .stream()
                        .map(person -> {
                            Map<String, String> personDetails = new LinkedHashMap<>();
                            personDetails.put("First Name", person.getFirstName());
                            personDetails.put("Last Name", person.getLastName());
                            personDetails.put("Phone Number", person.getPhoneNum());
                            personDetails.put("Age", String.valueOf(person.getMedicalRecords().calculateAge()));
                            return personDetails;
                        })
                        .collect(Collectors.toList());

                Map<String, Object> addressDetails = new LinkedHashMap<>();
                addressDetails.put("Address", address);
                addressDetails.put("People", peopleDetailsList);

                allPersonsList.add(addressDetails);
            }
            Map<String, Object> stationMap = new LinkedHashMap<>();
            stationMap.put("Station ID", String.valueOf(fireStation.getFireStationId()));
            stationMap.put("Station Name", fireStation.getStation());
            stationMap.put("Addresses", allPersonsList);

            allStationsMap.put("Station " + fireStation.getFireStationId(), Collections.singletonList(stationMap));
        }

        return new ResponseEntity<>(allStationsMap, HttpStatus.OK);
    }
}
