package com.backend.hospitaldb.patient;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping(path = "api/patient")
public class PatientController {

    private PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public List<Patient> listPatients() {
        return patientService.getPatients();
    }

    @GetMapping("{id}")
    public Patient getPatientByName(@PathVariable("id") Long id) {
        return patientService.getPatient(id);
    }


    @PostMapping
    public void addPatient(@RequestBody Patient patient) {
        System.out.println(patient);
        patientService.addNewPatient(patient);
    }

    @DeleteMapping("{id}")
    public void deletePatient(@PathVariable("id") Long id) {
        patientService.deletePatient(id);
    }

    @PutMapping
    public void updatePatient(@RequestBody Patient patient) {
        patientService.updatePatient(patient);
    }

}
