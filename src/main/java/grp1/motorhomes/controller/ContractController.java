package grp1.motorhomes.controller;

import grp1.motorhomes.model.Contract;
import grp1.motorhomes.model.Customer;
import grp1.motorhomes.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author Christian
 */
@Controller
public class ContractController {

    @Autowired
    ContractService contractService;

    /**
     * @author Christian
     * @param model
     * @return
     */
    @GetMapping("/contractIndex")
    public String rentalIndex(Model model) {
        List<Contract> contractList = contractService.fetchAllContracts();
        model.addAttribute("contract", contractList);
        return "home/contractIndex";
    }

    /**
     * @author Christian
     * @return
     */
    @GetMapping("/createContract")
    public String createRentalContract() {
        return "home/createContract";
    }

    /**
     * @author Chritian
     * @param contract
     * @return
     */
    @PostMapping("/createContract")
    public String createRentalContract(@ModelAttribute Contract contract) {
        contractService.createRentalContract(contract);
        return "redirect:/contractIndex";
    }

}
