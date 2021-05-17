package grp1.motorhomes.controller;


import grp1.motorhomes.model.Motorhome;
import grp1.motorhomes.service.MotorhomeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

// made with help from https://spring.io/guides/gs/testing-web/


@WebMvcTest(MotorhomeController.class)
public class MotorhomeControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    MotorhomeService motorhomeService;

    @Test
    public void motorHomeFetchTest() throws Exception {
        List<Motorhome> motorhomes = new ArrayList<Motorhome>();

        for(int i = 1 ; i <= 10 ; i++) {
            Motorhome motorhome = new Motorhome();
            motorhome.setLicencePlate("licencePlate" + i);
            motorhome.setType("type" + i);
            motorhome.setBrand("brand" + i);
            motorhome.setModel("model" + i);
            motorhome.setDescription("description" + i);
            motorhome.setImagePath("image" + i);
            motorhomes.add(motorhome);
        }
        when(motorhomeService.fetchAllMotorhomes()).thenReturn(motorhomes);

        mockMvc.perform(get("/motorhomeIndex"));

    }
}
