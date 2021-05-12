package grp1.motorhomes.service;

import grp1.motorhomes.model.Motorhome;
import grp1.motorhomes.repository.MotorhomeRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotorhomeService {



    public List<Motorhome> fetchAllMotorhomes() {
        return MotorhomeRepo.fetchAllMotorhomes();
    }

/*
    public void addMotorhome(Motorhome motorhome) {
        motorhomeRepo.addMotorhome(motorhome);
    }

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