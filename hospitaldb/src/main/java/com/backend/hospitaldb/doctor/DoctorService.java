package com.backend.hospitaldb.doctor;

import com.backend.hospitaldb.exception.DoctorNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    private DoctorDataAccessServicePG doctorDataAccessService;

    public DoctorService(DoctorDataAccessServicePG doctorDataAccessService) {
        this.doctorDataAccessService = doctorDataAccessService;
    }

    public List<Doctor> getDoctors() {
        return doctorDataAccessService.selectAllDoctors();
    }

    public void addNewDoctor(Doctor doctor) {
        int result = doctorDataAccessService.insertDoctor(doctor);
        if (result != 1) {
            throw new IllegalStateException("Error - please try again. Make sure all information entered is correct.");
        }
    }

    public Doctor getDoctor(Long doctorID) {
        return doctorDataAccessService.selectAllDoctors()
                .stream()
                .filter(doctor -> doctor.getDoctorID().equals(doctorID))
                .findFirst()
                .orElseThrow(() -> new DoctorNotFoundException("Doctor ID " + doctorID + " not found"));
    }

    public int deleteDoctor(Long doctorID) {
        for (Doctor doctor : doctorDataAccessService.selectAllDoctors()) {
            if (doctor.getDoctorID().equals(doctorID)) {
                doctorDataAccessService.deleteDoctor(doctor);
                return 1;
            }
        }
        throw new DoctorNotFoundException("Doctor ID " + doctorID + " not found");
    }

    public int updateDoctor(Doctor doctor) {
        for (Doctor doctorInDB : doctorDataAccessService.selectAllDoctors()) {
            if (doctorInDB.getDoctorID().equals(doctor.getDoctorID())) {
                doctorDataAccessService.deleteDoctor(doctorInDB);
                doctorDataAccessService.insertDoctor(doctor);
                return 1;
            }
        }
        throw new DoctorNotFoundException("Doctor not found");
    }
}
