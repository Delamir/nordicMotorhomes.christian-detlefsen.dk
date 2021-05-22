package grp1.motorhomes.controller;

import grp1.motorhomes.model.Contract;
import grp1.motorhomes.service.ContractService;
import grp1.motorhomes.service.CustomerService;
import grp1.motorhomes.service.MotorhomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Christian
 */
@Controller
public class ContractController {

    @Autowired
    ContractService contractService;

    @Autowired
    MotorhomeService motorhomeService;

    @Autowired
    CustomerService customerService;

    /**
     * @param model
     * @return
     * @author Christian
     */
    @GetMapping("/contractIndex")
    public String contractIndex(Model model) {
        model.addAttribute("contracts", contractService.fetchAllContracts());
        return "home/contractIndex";
    }

    @GetMapping("/contractIndex/closed")
    public String closedContractIndex(Model model) {
        model.addAttribute("contracts", contractService.fetchAllClosedContracts());
        return "home/contractIndex";
    }

    /**
     * @return
     * @author Christian
     */
    @GetMapping("/createContract")
    public String createContract(Model model) {
        model.addAttribute("motorhomes", motorhomeService.fetchAllMotorhomes());
        model.addAttribute("customers", customerService.fetchAllCustomers());
        return "home/createContract";
    }

    /**
     * @param contract
     * @return
     * @author Chritian
     */
    @PostMapping("/createContract")
    public String createContract(@ModelAttribute Contract contract) {
        System.out.println(contract);
        contractService.createContract(contract);
        return "redirect:/contractIndex";
    }

    /**
     * @param contractId
     * @param model
     * @return
     * @author Sverri
     */
    @GetMapping("/editContract/{contractId}")
    public String editContract(@PathVariable int contractId, Model model) {
        model.addAttribute("contract", contractService.findContract(contractId));
        model.addAttribute("motorhomes", motorhomeService.fetchAllMotorhomes());
        model.addAttribute("customers", customerService.fetchAllCustomers());
        return "home/editContract";
    }

    /**
     * @param contract
     * @return
     * @author Sverri
     */
    @PostMapping("/editContract")
    public String editContract(@ModelAttribute Contract contract) {
        contractService.editContract(contract);
        return "redirect:/contractIndex";
    }

    /**
     * @param contractId
     * @return
     * @author Sverri
     */
    @GetMapping("/deleteContract/{contractId}")
    public String deleteContract(@PathVariable int contractId) {
        contractService.deleteContract(contractId);
        return "redirect:/contractIndex";
    }

    /**
     * @author Patrick
     * @param contractId
     * @param model
     * @return
     */
    @GetMapping("/deliverContract/{contractId}")
    public String deliverContract(@PathVariable int contractId, Model model) {
        model.addAttribute("contract", contractService.findContract(contractId));
        return "home/deliverContract";
    }

    /**
     * @author Patrick
     * @param contract
     * @return
     */
    @PostMapping("/deliverContract")
    public String deliverContract(@ModelAttribute Contract contract) {
        contractService.deliverContract(contract);
        return "redirect:/contractIndex";
    }

    /**
     * @author Joachim
     * @param contractId
     * @param model
     * @return
     */
    @GetMapping("/pickupContract/{contractId}")
    public String pickupContract(@PathVariable int contractId, Model model){
        model.addAttribute("contract", contractService.findContract(contractId));
        return "home/pickupContract";
    }

    /**
     * @author Joachim
     * @param contract
     * @return
     */
    @PostMapping("/pickupContract")
    public String pickupContract(@ModelAttribute Contract contract) {
        contractService.pickupContract(contract);
        return "redirect:/contractIndex";
    }

    /**
     * @author Joachim
     * @return
     */
    @GetMapping("/closeContract/{contractId}")
    public String closeContract(@PathVariable int contractId, Model model) {
        model.addAttribute("contract", contractService.findContract(contractId));
        System.out.println(contractService.findContract(contractId));
        return "home/closeContract";
    }

    /**
     * @author Joachim
     * @param contract
     * @return
     */
    @PostMapping("/closeContract")
    public String closeContract(@ModelAttribute Contract contract) {
        contractService.closeContract(contract);
        return "redirect:/contractIndex";
    }

}
