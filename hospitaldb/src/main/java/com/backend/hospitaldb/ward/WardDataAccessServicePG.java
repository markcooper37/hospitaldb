package com.backend.hospitaldb.ward;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.enterprise.inject.spi.Bean;
import java.util.List;

@Repository

public class WardDataAccessServicePG implements WardDAO {

    private JdbcTemplate jdbcTemplate;

    public WardDataAccessServicePG (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Ward> selectAllWards() {
        String selectSql = """
                SELECT * FROM ward
                """;
        return jdbcTemplate.query(selectSql, new BeanPropertyRowMapper(Ward.class));
    }

    public int insertWard(Ward ward) {
        String insertSql = """
        INSERT INTO ward(wardId, wardName, hospital) VALUES(?, ?, ?)
        """;
        int result = jdbcTemplate.update(insertSql, ward.getWardId(), ward.getWardName(), ward.getHospital());
        return result;
    }

    public int deleteWard(Ward ward) {
        String deleteSql = """
                DELETE FROM ward WHERE wardId = ?
                """;
        int result =  jdbcTemplate.update(deleteSql, ward.getWardId());
        return result;
    }
}
