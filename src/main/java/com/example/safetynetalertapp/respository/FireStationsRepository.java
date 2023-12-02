package com.example.safetynetalertapp.respository;

import com.example.safetynetalertapp.model.FireStations;
import com.example.safetynetalertapp.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FireStationsRepository extends JpaRepository<FireStations,Long> {

    @Query("SELECT p FROM Person p WHERE p.fireStations.fireStationId = :fireStationId")
    List<Person> findPeopleByFireStationId(@Param("fireStationId") Long fireStationId);
    List<FireStations> findFireStationsByAddress(String address);
    List<FireStations> findAllByFireStationIdIn (List<Long> fireStationId);
}
