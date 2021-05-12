package grp1.motorhomes.service;

import grp1.motorhomes.model.Contract;
import grp1.motorhomes.repository.ContractRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Christian
 */
@Service
public class ContractService {

    @Autowired
    ContractRepo contractRepo;

    public List<Contract> fecthAllContracts() {
        return contractRepo.fetchAllContracts();
    }

    public void createRentalContract(Contract contract) {
        contractRepo.createRentalContract(contract);
    }
}
