package com.example.safetynetalertapp.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "fireStation")
public class FireStations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fireStationId;
    @Column(name = "address")
    private String address;
    @Column(name = "station")
    private String station;
    @OneToMany(mappedBy = "fireStations")
    private List<Person> persons;

    public FireStations(){}

    public FireStations(Long fireStationId, String address, String station) {
        this.fireStationId = fireStationId;
        this.address = address;
        this.station = station;
    }

    public Long getFireStationId() {
        return fireStationId;
    }

    public void setFireStationId(Long fireStationId) {
        this.fireStationId = fireStationId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    @Override
    public String toString() {
        return "FireStation{" +
                "Fire Station Id=" + fireStationId +
                ", Address='" + address + '\'' +
                ", Station='" + station + '\'' +
                '}';
    }
}
