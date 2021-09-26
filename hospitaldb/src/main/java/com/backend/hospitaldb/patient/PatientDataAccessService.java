package com.backend.hospitaldb.patient;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

@Repository
public class PatientDataAccessService implements PatientDAO {

    private static List<Patient> patientDatabase;

    public PatientDataAccessService() {
        LocalDate dob = LocalDate.of(1952, 7, 13);
        LocalDate dateAdmission = LocalDate.of(2021, 8, 17);
        LocalDate dateRelease = LocalDate.of(2021, 9, 12);
        Patient susanJones = new Patient(1L, "Susan", "Jones", SEX.F, dob, 69, SMOKER.YES, "Cancer", 7, dateAdmission, dateRelease, COVIDRISK.High, ASSESSMENTRISK.High, 1L);
        patientDatabase = new ArrayList<>();
        patientDatabase.add(susanJones);
    }

    public List<Patient> selectAllPatients() {
        return patientDatabase;
    }

    public int insertPatient(Patient patient) {
        if (patientDatabase.contains(patient)) {
            return 0;
        } else {
            patientDatabase.add(patient);
            return 1;
        }
    }

    public int deletePatient(Patient patient) {
        if (patientDatabase.contains(patient)) {
            patientDatabase.remove(patient);
            return 1;
        } else {
            return 0;
        }
    }
}
