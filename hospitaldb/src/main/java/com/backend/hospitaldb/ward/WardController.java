package com.backend.hospitaldb.ward;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/ward")
public class WardController {

    private WardService wardService;

    public WardController(WardService wardService) {
        this.wardService = wardService;
    }

    @GetMapping
    public List<Ward> listWard() {
        return wardService.getWards();
    }

    @GetMapping("{wardId}")
    public Ward getWardById(@PathVariable("wardId") int wardId) {
        return wardService.getWard(wardId);
    }


    @PostMapping
    public void addWard(@RequestBody Ward ward) {
        System.out.println(ward);
        wardService.addNewWard(ward);
    }

    @DeleteMapping("{wardId}")
    public void deleteWard(@PathVariable("wardId") int wardId) {
        wardService.deleteWard(wardId);
    }

    @PutMapping
    public void updateWard(@RequestBody Ward ward)  {
        wardService.updateWard(ward);
    }

}
