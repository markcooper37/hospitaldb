package com.backend.hospitaldb.doctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/doctor")
public class DoctorController {

    private DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public List<Doctor> listDoctors() {
        return doctorService.getDoctors();
    }

    @GetMapping("{doctorID}")
    public Doctor getDoctorByID(@PathVariable("doctorID") Long doctorID) {
        return doctorService.getDoctor(doctorID);
    }

    @PostMapping
    public void addDoctor(@RequestBody Doctor doctor) {
        System.out.println(doctor);
        doctorService.addNewDoctor(doctor);
    }

    @DeleteMapping
    public void deleteDoctor(@PathVariable("doctorID") Long doctorID) {
        doctorService.deleteDoctor(doctorID);
    }

    @PutMapping
    public void updateDoctor(@RequestBody Doctor doctor) {
        doctorService.updateDoctor(doctor);
    }
}
