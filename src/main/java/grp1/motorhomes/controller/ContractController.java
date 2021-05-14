package grp1.motorhomes.controller;

import grp1.motorhomes.model.Contract;
import grp1.motorhomes.model.Customer;
import grp1.motorhomes.model.Motorhome;
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

import java.util.List;

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
     * @author Christian
     * @param model
     * @return
     */
    @GetMapping("/contractIndex")
    public String contractIndex(Model model) {
        List<Contract> contractList = contractService.fetchAllContracts();
        model.addAttribute("contracts", contractList);
        return "home/contractIndex";
    }

    /**
     * @author Christian
     * @return
     */
    @GetMapping("/createContract")
    public String createContract(Model model) {
        List<Motorhome> motorhomeList = motorhomeService.fetchAllMotorhomes();
        List<Customer> customerList = customerService.fetchAllCustomers();
        model.addAttribute("motorhomes", motorhomeList);
        model.addAttribute("customers", customerList);
        return "home/createContract";
    }

    /**
     * @author Chritian
     * @param contract
     * @return
     */
    @PostMapping("/createContract")
    public String createContract(@ModelAttribute Contract contract) {
        System.out.println(contract);
        contractService.createContract(contract);
        return "redirect:/contractIndex";
    }

    /**
     * @author Sverri
     * @param contractId
     * @param model
     * @return
     */
    @GetMapping("/editContract/{contractId}")
    public String editContract(@PathVariable int contractId, Model model) {
        Contract contract = contractService.findContract(contractId);
        model.addAttribute("contract", contract);
        List<Motorhome> motorhomeList = motorhomeService.fetchAllMotorhomes();
        List<Customer> customerList = customerService.fetchAllCustomers();
        model.addAttribute("motorhomes", motorhomeList);
        model.addAttribute("customers", customerList);
        return "home/editContract";
    }

    /**
     * @author Sverri
     * @param contract
     * @return
     */
    @PostMapping("/editContract")
    public String editContract(@ModelAttribute Contract contract){
        contractService.editContract(contract);
        return "redirect:/contractIndex";
    }

    /**
     * @author Sverri
     * @param contractId
     * @return
     */
    @GetMapping("/deleteContract/{contractId}")
    public String deleteContract(@PathVariable int contractId){
        contractService.deleteContract(contractId);
        return "redirect:/contractIndex";
    }



}
