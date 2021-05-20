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

    /**
     * @author Christian
     * @return
     */
    public List<Contract> fetchAllContracts() {
        return contractRepo.fetchAllContracts();
    }

    /**
     * @author Christian
     * @param contract
     */
    public void createContract(Contract contract) {
        contractRepo.createContract(contract);
    }

    /**
     * @author Sverri
     * @param contractId
     * @return
     */
    public Contract findContract(int contractId) {
        return contractRepo.findContract(contractId);
    }

    /**
     * @author Sverri
      * @param contract
     */
    public void editContract(Contract contract) {
        contractRepo.editContract(contract);
    }

    /**
     * @author Sverri
     */
    public void deleteContract(int contractId){
        contractRepo.deleteContract(contractId);
    }

    public void deliverContract(Contract contract) {
        contractRepo.deliverContract(contract);
    }
}
