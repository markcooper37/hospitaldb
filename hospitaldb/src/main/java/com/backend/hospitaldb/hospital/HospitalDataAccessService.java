package com.backend.hospitaldb.hospital;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.ArrayList;

@Repository
public class HospitalDataAccessService implements HospitalDAO {

    private static List<Hospital> hospitalDatabase;

    public HospitalDataAccessService() {
        Hospital holbyCity = new Hospital(1L, "Holby City", "Eldon Avenue", "Borehamwood", "Hertfordshire", "UK", "WD6 1NL");
        hospitalDatabase = new ArrayList<>();
        hospitalDatabase.add(holbyCity);
    }

    public List<Hospital> selectAllHospitals() {
        return hospitalDatabase;
    }

    public int insertHospital(Hospital hospital) {
        if (hospitalDatabase.contains(hospital)) {
            return 0;
        } else {
            hospitalDatabase.add(hospital);
            return 1;
        }
    }

    public int deleteHospital(Hospital hospital) {
        if (hospitalDatabase.contains(hospital)) {
            hospitalDatabase.remove(hospital);
            return 1;
        } else {
            return 0;
        }
    }
}


