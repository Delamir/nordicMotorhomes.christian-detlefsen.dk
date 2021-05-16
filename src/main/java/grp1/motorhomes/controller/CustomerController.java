package grp1.motorhomes.controller;

import grp1.motorhomes.model.Customer;
import grp1.motorhomes.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author Joachim
 */
@Controller
public class CustomerController {

    @Autowired
    CustomerService customerService;

    /**
     * @author Joachim
     */
    @GetMapping("/customerIndex")
    public String customerIndex(Model model) {
        List<Customer> customerList = customerService.fetchAllCustomers();
        model.addAttribute("customers", customerList);
        return "home/customerIndex";
    }

    /**
     * @author Joachim
     */
    @GetMapping("/createCustomer")
    public String createCustomer(Model model) {
        List<Customer> customerList = customerService.fetchAllCustomers();
        model.addAttribute("customers", customerList);
        return "home/createCustomer";
    }

    /**
     * @author Joachim
     */
    @PostMapping("/createCustomer")
    public String createCustomer(@ModelAttribute Customer customer) {
        System.out.println(customer);
        customerService.createCustomer(customer);
        return "redirect:/customerIndex";
    }

    /**
     * @author Joachim
     */
    @GetMapping("/editCustomer/{customerNumber}")
    public String editCustomer(@PathVariable ("customerNumber") int customerNumber, Model model) {
        Customer customer = customerService.findCustomer(customerNumber);
        model.addAttribute("customer", customer);
        return "home/editCustomer";
    }

    /**
     * @author Joachim
     */
    @PostMapping("editCostumer")
    public String editCustomer(@ModelAttribute Customer customer) {
        customerService.editCustomer(customer);
        return "redirect:/customerIndex";
    }

    /**
     * @author Joachim
     */
    @GetMapping("/deleteCustomer/{customerNumber}")
    public String deleteCustomer(@PathVariable("customerNumber") int customerNumber) {
        customerService.deleteCustomer(customerNumber);
        return "redirect:/customerIndex";
    }

}