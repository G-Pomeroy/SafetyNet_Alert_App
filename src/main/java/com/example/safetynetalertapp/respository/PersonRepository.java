package com.example.safetynetalertapp.respository;

import com.example.safetynetalertapp.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

    List<Person> findByAddress(String address);
    List<Person> findByAddressIn(List<String> addresses);
    List<Person> findByFireStations_FireStationId(Long fireStationId);
    List<Person> findByFirstNameAndLastName (String firstName, String lastName);
    List<Person> findByCity(String city);

}
