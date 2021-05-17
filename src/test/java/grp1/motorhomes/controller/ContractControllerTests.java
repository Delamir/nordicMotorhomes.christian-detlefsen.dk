package grp1.motorhomes.controller;

import grp1.motorhomes.model.Contract;
import grp1.motorhomes.service.ContractService;
import grp1.motorhomes.service.CustomerService;
import grp1.motorhomes.service.MotorhomeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.containsString;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(ContractController.class)
public class ContractControllerTests {


    @Autowired
    MockMvc mockMvc;

    @MockBean
    ContractService contractService;

    @MockBean
    MotorhomeService motorhomeService;

    @MockBean
    CustomerService customerService;


    @Test
    public void contractFetchTest() throws Exception {
        List<Contract> contracts = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            Contract contract = new Contract();
            contract.setFromDate("2021-05-17T13:20");
            contract.setToDate("2021-06-17T13:20");
            contract.setContractId(1);
            contract.setCustomerNumber(1);
            contract.setMotorhome("Jack");
            contract.setOdometer(95000);
            contract.setPrice(1337);
        }

        when(contractService.fetchAllContracts()).thenReturn(contracts);

        mockMvc.perform(get("/contractIndex"));

    }

    @Test
    public void contractEditTest() throws Exception {
        Contract contract = new Contract(1, Timestamp.valueOf("2021-06-17 10:20:20"), Timestamp.valueOf("2021-07-17 10:20:20"),
                6900, 1, 1337, "Caravan");

        ContractController.
    }

}
