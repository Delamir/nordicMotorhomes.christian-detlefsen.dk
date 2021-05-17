package grp1.motorhomes.service;

import grp1.motorhomes.model.AutoService;
import grp1.motorhomes.repository.AutoServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * @author Christian
 */
@Service
public class AutoServiceService {

    @Autowired
    AutoServiceRepo autoServiceRepo;

    /**
     * @author Chrsitian
     * @return
     */
    /*
    public List<AutoService> fetchAllAutoServices() {
        return autoServiceRepo.fetchAllAutoServices();
    }

     */
}
