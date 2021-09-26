package com.backend.hospitaldb.patient;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Repository

public class PatientDataAccessServicePG implements PatientDAO {

    private JdbcTemplate jdbcTemplate;

    public PatientDataAccessServicePG (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Patient> selectAllPatients() {
        String selectSql = """
                SELECT * FROM patient
                """;
        return jdbcTemplate.query(selectSql, new BeanPropertyRowMapper(Patient.class));
    }

    public int insertPatient(Patient patient) {
        String insertSql = """
        INSERT INTO patient(id, firstName, lastName, sex, dob, age, smoker, illness, ward, dateAdmission, dateRelease, covidrisk, assessmentrisk, doctor) VALUES(?, ?, ?, CAST(? AS patientsex), ?, ?, CAST(? AS patientsmoker), ?, ?, ?, ?, CAST(? AS patientcovidrisk), CAST(? AS patientassessmentrisk), ?)
        """;
        int result = jdbcTemplate.update(insertSql, patient.getId(), patient.getFirstName(), patient.getLastName(), patient.getSex().toString(), patient.getDob(), patient.getAge(), patient.getSmoker().toString(), patient.getIllness(), patient.getWard(), patient.getDateAdmission(), patient.getDateRelease(), patient.getCovidrisk().toString(), patient.getAssessmentrisk().toString(), patient.getDoctor());
        return result;
    }

    public int deletePatient(Patient patient) {
        String deleteSql = """
                DELETE FROM patient WHERE id = ?
                """;
        int result = jdbcTemplate.update(deleteSql, patient.getId());
        return result;
    }

}
