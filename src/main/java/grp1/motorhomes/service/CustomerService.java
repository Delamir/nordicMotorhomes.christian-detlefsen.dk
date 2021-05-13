package grp1.motorhomes.service;

import grp1.motorhomes.model.Customer;
import grp1.motorhomes.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepo customerRepo;

    /**
     * @author Joachim
     */
    public List<Customer> fetchAllCustomers() {
        return customerRepo.fetchAllCustomers();
    }

    /**
     * @author Joachim
     */
/*
    public void createCustomer(Customer customer) {
        customerRepo.createCustomer(customer);
    }
    */
}
