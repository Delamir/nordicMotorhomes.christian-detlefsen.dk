package grp1.motorhomes.controller;

import grp1.motorhomes.model.Customer;
import grp1.motorhomes.service.CustomerService;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.containsString;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * @author Joachim
 */

@WebMvcTest(CustomerController.class)
public class CustomerControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CustomerService customerService;

    static List<Customer> customers;

    /**
     * @author Joachim
     */

    @BeforeAll
    public static void before() {
        customers = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            Customer customer = new Customer();
            customer.setCustomerNumber(1);
            customer.setCity("Glostrup" + i);
            customer.setStreet("Vestergårdsvej 123" + i);
            customer.setPostCode(2600);
            customer.setLicenceNumber("AD598741" + i);
            customer.setName("Bjørn Bruno" + i);
            customers.add(customer);

        }
    }

    /**
     * @author Joachim
     */

    @Test
    public void customerFetchTest() throws Exception {

        when(customerService.fetchAllCustomers()).thenReturn(customers);

        mockMvc.perform(get("/customerIndex"))
                .andExpect(model().attribute("customers", customers))
                .andExpect(content().string(containsString("Customer Management")))
                .andExpect(content().string(containsString("Glostrup2")))
                .andExpect(content().string(containsString("Vestergårdsvej 1235")))
                .andExpect(content().string(containsString("2600")))
                .andExpect(content().string(containsString("AD5987419")))
                .andExpect(content().string(containsString("Bjørn Bruno1")))
                .andExpect(status().isOk());
    }

    /**
     * @author Joachim
     */

    @Test
    public void createTest() throws Exception {

        mockMvc.perform(get("/createCustomer"))
                .andExpect(content().string(containsString("Customer Name: ")))
                .andExpect(content().string(containsString("Licence Number: ")))
                .andExpect(content().string(containsString("Street: ")))
                .andExpect(content().string(containsString("City: ")))
                .andExpect(content().string(containsString("Postcode: ")))
                .andExpect(status().isOk());

        mockMvc.perform(post("/createCustomer")
                .param("customerNumber", customers.get(0).getCustomerNumber() + "")
                .param("name", customers.get(0).getName())
                .param("licenceNumber", customers.get(0).getLicenceNumber())
                .param("street", customers.get(0).getStreet())
                .param("city", customers.get(0).getCity())
                .param("postCode", customers.get(0).getPostCode() + ""))
                .andExpect(status().is(302));

        ArgumentCaptor<Customer> argsCap = ArgumentCaptor.forClass(Customer.class);
        Mockito.verify(customerService).createCustomer(argsCap.capture());

        assertEquals(argsCap.getValue().toString(), customers.get(0).toString());
    }

    /**
     * @author Joachim
     */

    @Test
    public void editTest() throws Exception {

        when(customerService.findCustomer(customers.get(0).getCustomerNumber())).thenReturn(customers.get(0));

        mockMvc.perform(get("/editCustomer/" + customers.get(0).getCustomerNumber()))
                .andExpect(model().attribute("customer", customers.get(0)))
                .andExpect(content().string(containsString("Edit a Customer")))
                .andExpect(content().string(containsString("Glostrup")))
                .andExpect(content().string(containsString("Vestergårdsvej 123")))
                .andExpect(content().string(containsString("2600")))
                .andExpect(content().string(containsString("AD598741")))
                .andExpect(content().string(containsString("Bjørn Bruno")))
                .andExpect(status().isOk());
    }
}
