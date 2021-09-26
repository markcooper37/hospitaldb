package com.backend.hospitaldb.hospital;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Repository
public class HospitalDataAccessServicePG implements HospitalDAO {

    private JdbcTemplate jdbcTemplate;

    public HospitalDataAccessServicePG (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Hospital> selectAllHospitals() {
        String selectSql = """
                SELECT * FROM hospital
                """;
        return jdbcTemplate.query(selectSql, new BeanPropertyRowMapper(Hospital.class));
    }

    public int insertHospital(Hospital hospital) {
        String insertSql = """
        INSERT INTO hospital(hospitalID, hospitalName, street, city, state, country, postalCode) VALUES(?, ?, ?, ?, ?, ?, ?)
        """;
        int result = jdbcTemplate.update(insertSql, hospital.getHospitalID(), hospital.getHospitalName(), hospital.getStreet(), hospital.getCity(), hospital.getState(), hospital.getCountry(), hospital.getPostalCode());
        return result;
    }

    public int deleteHospital(Hospital hospital) {
        String deleteSql = """
                DELETE FROM hospital WHERE hospitalID = ?
                """;
        int result =  jdbcTemplate.update(deleteSql, hospital.getHospitalID());
        return result;
    }
}
