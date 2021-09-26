package com.backend.hospitaldb.ward;

import com.backend.hospitaldb.exception.WardNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WardService {

    private WardDataAccessServicePG wardDataAccessService;

    public WardService(WardDataAccessServicePG wardDataAccessService) {
        this.wardDataAccessService = wardDataAccessService;
    }

    public List<Ward> getWards() {
        return wardDataAccessService.selectAllWards();
    }

    public void addNewWard(Ward ward) {
        int result = wardDataAccessService.insertWard(ward);
        if (result != 1) {
            throw new IllegalStateException("Error - please try again. Make sure all information entered is correct.");
        }
    }

    public Ward getWard(int wardId) {
        return wardDataAccessService.selectAllWards()
                .stream()
                .filter(ward -> ward.getWardId() == (wardId))
                .findFirst()
                .orElseThrow(() -> new WardNotFoundException("Ward ID " + wardId + " not found"));
    }

    public int deleteWard(int wardId) {
        for (Ward ward : wardDataAccessService.selectAllWards()) {
            if (ward.getWardId() == (wardId)) {
                wardDataAccessService.deleteWard(ward);
                return 1;
            }
        }
        throw new WardNotFoundException("Ward ID " + wardId + " not found");
    }

    public int updateWard(Ward ward) {
        for (Ward wardInDB : wardDataAccessService.selectAllWards()) {
            if (wardInDB.getWardId() == (ward.getWardId())) {
                wardDataAccessService.deleteWard(wardInDB);
                wardDataAccessService.insertWard(ward);
                return 1;
            }
        }
        throw new WardNotFoundException("Ward not found");
    }

}
