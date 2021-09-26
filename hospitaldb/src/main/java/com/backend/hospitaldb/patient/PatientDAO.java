package com.backend.hospitaldb.patient;

import java.util.List;

public interface PatientDAO {

    List<Patient> selectAllPatients();
    int insertPatient(Patient patient);
    int deletePatient(Patient patient);

}
