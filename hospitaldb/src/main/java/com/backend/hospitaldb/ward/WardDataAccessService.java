package com.backend.hospitaldb.ward;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.ArrayList;

@Repository
public class WardDataAccessService implements WardDAO {

    private static List<Ward> Warddb;

    public WardDataAccessService() {
        Ward oncologyWard = new Ward (1, "Oncology", 1L);
        Warddb = new ArrayList<>();
        Warddb.add(oncologyWard);
    }

    public List<Ward> selectAllWards() {
        return Warddb;
    }

    public int insertWard(Ward ward) {
        if (Warddb.contains(ward)) {
            return 0;
        } else {
            Warddb.add(ward);
            return 1;
        }
    }

    public int deleteWard(Ward ward) {
        if (Warddb.contains(ward)) {
            Warddb.remove(ward);
            return 1;
        } else {
            return 0;
        }
    }

}
