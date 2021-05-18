package grp1.motorhomes.controller;

import grp1.motorhomes.model.Contract;
import grp1.motorhomes.model.Motorhome;
import grp1.motorhomes.service.ContractService;
import grp1.motorhomes.service.CustomerService;
import grp1.motorhomes.service.MotorhomeService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    static List<Contract> contracts;

    /**
     * @author Christian
     */
    @BeforeAll
    public static void before() {
        contracts = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            Contract contract = new Contract();
            contract.setContractId(i);
            contract.setFromDate("2021-05-17T13:20");
            contract.setToDate("2021-06-17T13:20");
            contract.setCustomerNumber(1);
            contract.setMotorhome("Jack");
            contract.setOdometer(95000);
            contract.setPrice(1337);
            contracts.add(contract);
        }
    }

    /**
     * @throws Exception
     * @author Christian and Joachim
     */
    @Test
    public void contractFetchTest() throws Exception {


        when(contractService.fetchAllContracts()).thenReturn(contracts);

        mockMvc.perform(get("/contractIndex"));

    }

    /**
     * @throws Exception
     * @author Christian
     */
    @Test
    public void createTest() throws Exception {

        mockMvc.perform(get("/createContract"))
                .andExpect(content().string(containsString("Contract Start Date:")))
                .andExpect(content().string(containsString("Contract End Date:")))
                .andExpect(content().string(containsString("Odometer:")))
                .andExpect(content().string(containsString("Price:")))
                .andExpect(content().string(containsString("Customer:")))
                .andExpect(content().string(containsString("Motorhome:")))
                .andExpect(content().string(containsString("Create Contract")))
                .andExpect(status().isOk());


        mockMvc.perform(post("/createContract")
                .param("fromDate", contracts.get(0).getFromDate().toString())
                .param("toDate", contracts.get(0).getToDate().toString())
                .param("odometer", contracts.get(0).getOdometer()+"")
                .param("price", contracts.get(0).getPrice()+"")
                .param("customerNumber", contracts.get(0).getCustomerNumber()+"")
                .param("motorhome", contracts.get(0).getMotorhome())
                .param("contractId", contracts.get(0).getContractId()+""))
                .andExpect(status().is(302));

        ArgumentCaptor<Contract> argsCap = ArgumentCaptor.forClass(Contract.class);
        Mockito.verify(contractService).createContract(argsCap.capture());

        assertEquals(contracts.get(0).toString(), argsCap.getValue().toString());
    }

    /**
     * @throws Exception
     * @author Christian
     */
    @Test
    public void editTest() throws Exception {

        mockMvc.perform(get("/contractEdit/"+contracts.get(0).getContractId()))
                .andExpect(model().attribute("contract", contracts.get(0)))
                .andExpect(content().string(containsString("2021-05-17T13:20")))
                .andExpect(content().string(containsString("2021-06-17T13:20")))
                .andExpect(content().string(containsString("Jack")))
                .andExpect(content().string(containsString("99000")))
                .andExpect(content().string(containsString("1337")))
                .andExpect(status().isOk());

    }
}
