package grp1.motorhomes.controller;

import grp1.motorhomes.model.Customer;
import grp1.motorhomes.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
        System.out.println(customerList);
        return "home/customerIndex";
    }

    /**
     * @author Joachim
     */

    @GetMapping("/createCustomer")
    public String createCustomer() {
        return "home/createCustomer";
    }

    /**
     * @author Joachim
     */

    /*
    @PostMapping("/createCustomer")
    public String createCustomer(@ModelAttribute Customer customer) {
        customerService.createCustomer(customer);
        return "redirect:/curstomerIndex";
    }

     */
}