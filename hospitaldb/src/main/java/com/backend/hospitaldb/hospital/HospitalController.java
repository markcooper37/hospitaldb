package com.backend.hospitaldb.hospital;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/hospital")
public class HospitalController {

    private HospitalService hospitalService;

    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @GetMapping
    public List<Hospital> listHospitals() {
        return hospitalService.getHospitals();
    }

    @GetMapping("{hospitalID}")
    public Hospital getHospitalByID(@PathVariable("hospitalID") Long hospitalID) {
        return hospitalService.getHospital(hospitalID);
    }

    @PostMapping
    public void addHospital(@RequestBody Hospital hospital) {
        System.out.println(hospital);
        hospitalService.addNewHospital(hospital);
    }

    @DeleteMapping
    public void deleteHospital(@PathVariable("hospitalID") Long hospitalID) {
        hospitalService.deleteHospital(hospitalID);
    }

    @PutMapping
    public void updateHospital(@RequestBody Hospital hospital) {
        hospitalService.updateHospital(hospital);
    }
}
