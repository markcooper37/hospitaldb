package com.backend.hospitaldb.doctor;

import java.util.List;

public interface DoctorDAO {

    List<Doctor> selectAllDoctors();
    int insertDoctor(Doctor doctor);
    int deleteDoctor(Doctor doctor);

}
