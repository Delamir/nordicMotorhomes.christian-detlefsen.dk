package grp1.motorhomes.service;

import grp1.motorhomes.model.Motorhome;
import grp1.motorhomes.repository.MotorhomeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotorhomeService {


    @Autowired
    MotorhomeRepo motorhomeRepo;

    public List<Motorhome> fetchAllMotorhomes() {
        return motorhomeRepo.fetchAllMotorhomes();
    }

    public void createMotorhome(Motorhome motorhome) {
        motorhomeRepo.createMotorhome(motorhome);
    }
/*
    public Motorhome findMotorhomeById(int id) {
        return motorhomeRepo.findMotorhomeById(id);
    }

    public void updateMotorhome(Motorhome motorhome) {
        motorhomeRepo.updateMotorhome(motorhome);
    }

    public void deleteMotorhome(int id) {
        motorhomeRepo.deleteMotorhome(id);
    }
*/

}