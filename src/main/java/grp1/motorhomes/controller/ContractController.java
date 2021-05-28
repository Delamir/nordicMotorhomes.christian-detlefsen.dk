package grp1.motorhomes.controller;

import grp1.motorhomes.model.Contract;
import grp1.motorhomes.service.ContractService;
import grp1.motorhomes.service.CustomerService;
import grp1.motorhomes.service.ExtraService;
import grp1.motorhomes.service.MotorhomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

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

    @Autowired
    ExtraService extraService;

    /**
     * @param model
     * @return
     * @author Christian
     */
    @GetMapping("/contractIndex")
    public String contractIndex(Model model) {
        model.addAttribute("contracts", contractService.fetchAllContracts());
        return "home/contract/contractIndex";
    }

    @GetMapping("/contractIndex/closed")
    public String closedContractIndex(Model model) {
        model.addAttribute("contracts", contractService.fetchAllClosedContracts());
        return "home/contract/contractIndex";
    }

    /**
     * @return
     * @author Christian
     */
    @GetMapping("/createContract")
    public String createContract(Model model) {
        return "home/contract/createContract";
    }

    /**
     * @author Sverri
     */
    @PostMapping("/getAvailableMotorhomes")
    public String getAvailableMotorhomes(@RequestParam String fromDate, @RequestParam String toDate, Model model){
        model.addAttribute("fromDate", fromDate);
        model.addAttribute("toDate", toDate);

        if (fromDate.length() != 0 && toDate.length() != 0) {
            model.addAttribute("motorhomes", motorhomeService.fetchMotorhomesBetween(LocalDateTime.parse(fromDate), LocalDateTime.parse(toDate)));
            model.addAttribute("customers", customerService.fetchAllCustomers());
            model.addAttribute("extras", extraService.fetchAllExtras());
        }
        return "home/contract/createContract";
    }

    /**
     * @param contract
     * @return
     * @author Chritian
     */
    @PostMapping("/createContract")
    public String createContract(@ModelAttribute Contract contract) {
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
        System.out.println(contractService.findContract(contractId));
        model.addAttribute("motorhomes", motorhomeService.fetchAllMotorhomes());
        model.addAttribute("customers", customerService.fetchAllCustomers());
        model.addAttribute("extras", extraService.fetchAllExtras());
        return "home/contract/editContract";
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
        return "redirect:/closedContracts";
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
        return "home/contract/deliverContract";
    }

    /**
     * @author Patrick
     * @param contract
     * @return
     */
    @PostMapping("/deliverContract")
    public String deliverContract(@ModelAttribute Contract contract, Model model) {

        try {
            contractService.deliverContract(contract);
            return "redirect:/contractIndex";
        } catch (Exception e){
            model.addAttribute("errorMessage", e.getMessage());
            return "redirect:/deliverContract/" + contract.getContractId();
        }
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
        return "home/contract/pickupContract";
    }

    /**
     * @author Joachim
     * @param contract
     * @return
     */
    @PostMapping("/pickupContract")
    public String pickupContract(@ModelAttribute Contract contract, Model model) {

        try {
            contractService.pickupContract(contract);
            motorhomeService.setAvailable(contract.getMotorhome().getLicencePlate(), false);
            return "redirect:/contractIndex";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "redirect:/pickupContract/" + contract.getContractId();
        }
    }

    /**
     * @author Joachim
     * @return
     */
    @GetMapping("/closeContract/{contractId}")
    public String closeContract(@PathVariable int contractId, Model model) {
        Contract contract = contractService.findContract(contractId);
        model.addAttribute("contract", contract);
        model.addAttribute("rentalPrice", contractService.calculatePrice(contract));
        return "home/contract/closeContract";
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

    /**
     * @author Joachim
     * @param model
     * @return
     */
    @GetMapping("/closedContracts")
    public String closedContracts(Model model ) {
        model.addAttribute("contracts", contractService.fetchAllClosedContracts());
        return "home/contract/closedContracts";
    }

    /**
     * @author Sverri
     */
    @PostMapping("/calculatePrice")
    public String calculatePrice(@RequestParam Integer contractId, @RequestParam Integer excessKm, Model model) {
        Contract contract = contractService.findContract(contractId);
        contract.setExcessKm(excessKm);
        contractService.editContract(contract);
        model.addAttribute("contract", contract);
        model.addAttribute("rentalPrice", contractService.calculatePrice(contract));
        return "home/contract/closeContract";
    }

    /**
     * @author Christian
     */
    @GetMapping("/cancelContract/{contractId}")
    public String cancelContract(@PathVariable int contractId, Model model) {
        Contract contract = contractService.findContract(contractId);
        model.addAttribute("contract", contract);
        model.addAttribute("cancellationFee", contractService.cancellationFee(contract));
        return "home/contract/cancelContract";
    }

    /**
     * @author Chritsian
     */
    @PostMapping("/cancellationFee")
    public String cancellationFee(@RequestParam Integer contractId, Model model) {
        Contract contract = contractService.findContract(contractId);
        contractService.editContract(contract);
        model.addAttribute("contract", contract);
        return "home/contract/cancelContract";
    }
}
