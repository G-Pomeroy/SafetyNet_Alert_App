package com.example.safetynetalertapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personId;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "address")
    private String address;
    @Column(name = "city")
    private String city;
    @Column(name = "postalCode")
    private String postalCode;
    @Column(name = "phoneNum")
    private String phoneNum;
    @Column(name = "email")
    private String email;
    @OneToOne(cascade = CascadeType.ALL)
    private MedicalRecords medicalRecords;
    @ManyToOne
    @JoinColumn(name = "fireStationId")
    private FireStations fireStations;

    //Constructors
    public Person() {
    }

    public Person(Long personId, String firstName, String lastName, String address, String city, String postalCode,
                  String phoneNum, String email, MedicalRecords medicalRecords, FireStations fireStations) {
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.phoneNum = phoneNum;
        this.email = email;
        this.medicalRecords = medicalRecords;
        this.fireStations = fireStations;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public MedicalRecords getMedicalRecords() {
        return medicalRecords;
    }

    public void setMedicalRecords(MedicalRecords medicalRecords) {
        this.medicalRecords = medicalRecords;
    }

    public FireStations getFireStations() {
        return fireStations;
    }

    public void setFireStations(FireStations fireStations) {
        this.fireStations = fireStations;
    }

    @Override
    public String toString() {
        return "Person{" +
                "personId=" + personId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", email='" + email + '\'' +
                ", medicalRecords=" + medicalRecords +
                ", fireStations=" + fireStations +
                '}';
    }
}