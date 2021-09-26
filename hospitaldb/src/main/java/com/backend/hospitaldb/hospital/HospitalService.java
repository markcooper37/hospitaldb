package com.backend.hospitaldb.hospital;

import com.backend.hospitaldb.exception.HospitalNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalService {

    private HospitalDataAccessServicePG hospitalDataAccessService;

    public HospitalService(HospitalDataAccessServicePG hospitalDataAccessService) {
        this.hospitalDataAccessService = hospitalDataAccessService;
    }

    public List<Hospital> getHospitals() {
        return hospitalDataAccessService.selectAllHospitals();
    }

    public void addNewHospital(Hospital hospital) {
        int result = hospitalDataAccessService.insertHospital(hospital);
        if (result != 1) {
            throw new IllegalStateException("Error - please try again. Make sure all information entered is correct.");
        }
    }

    public Hospital getHospital(Long hospitalID) {
        return hospitalDataAccessService.selectAllHospitals()
                .stream()
                .filter(hospital -> hospital.getHospitalID().equals(hospitalID))
                .findFirst()
                .orElseThrow(() -> new HospitalNotFoundException("Hospital ID " + hospitalID + " not found"));
    }

    public int deleteHospital(Long hospitalID) {
        for (Hospital hospital : hospitalDataAccessService.selectAllHospitals()) {
            if (hospital.getHospitalID().equals(hospitalID)) {
                hospitalDataAccessService.deleteHospital(hospital);
                return 1;
            }
        }
        throw new HospitalNotFoundException("Hospital ID " + hospitalID + " not found");

    }

    public int updateHospital(Hospital hospital) {
        for (Hospital hospitalInDB : hospitalDataAccessService.selectAllHospitals()) {
            if (hospitalInDB.getHospitalID().equals(hospital.getHospitalID())) {
                hospitalDataAccessService.deleteHospital(hospitalInDB);
                hospitalDataAccessService.insertHospital(hospital);
                return 1;
            }
        }
        throw new HospitalNotFoundException("Hospital not found");
    }

}
