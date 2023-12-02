package com.example.safetynetalertapp.respository;

import com.example.safetynetalertapp.model.MedicalRecords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalRecordsRepository extends JpaRepository<MedicalRecords,Long> {
}
