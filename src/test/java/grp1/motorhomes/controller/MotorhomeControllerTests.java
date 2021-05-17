package grp1.motorhomes.controller;


import grp1.motorhomes.model.Motorhome;
import grp1.motorhomes.service.MotorhomeService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// made with help from https://spring.io/guides/gs/testing-web/

@WebMvcTest(MotorhomeController.class)
public class MotorhomeControllerTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    MotorhomeController motorhomeController;

    @MockBean
    MotorhomeService motorhomeService;

    static List<Motorhome> motorhomes;

    @BeforeAll
    public static void before() {
        motorhomes = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            Motorhome motorhome = new Motorhome();
            motorhome.setLicencePlate("licencePlate" + i);
            motorhome.setType("type" + i);
            motorhome.setBrand("brand" + i);
            motorhome.setModel("model" + i);
            motorhome.setDescription("description" + i);
            motorhomes.add(motorhome);
        }
    }

    /**
     * @throws Exception
     * @author Sverri
     */
    @Test
    public void motorHomeFetchTest() throws Exception {

        when(motorhomeService.fetchAllMotorhomes()).thenReturn(motorhomes);

        mockMvc.perform(get("/motorhomeIndex"))
                .andExpect(model().attribute("motorhomes", motorhomes))
                .andExpect(content().string(containsString("Motorhome Management")))
                .andExpect(content().string(containsString("brand1")))
                .andExpect(content().string(containsString("type5")))
                .andExpect(content().string(containsString("description10")))
                .andExpect(status().isOk());
    }

    /**
     * @author Sverri
     * @throws Exception
     */
    @Test
    public void createTest() throws Exception {

        mockMvc.perform(get("/createMotorhome"))
                .andExpect(content().string(containsString("Licence plate:")))
                .andExpect(content().string(containsString("Type:")))
                .andExpect(content().string(containsString("Brand:")))
                .andExpect(content().string(containsString("Model:")))
                .andExpect(content().string(containsString("Description:")))
                .andExpect(content().string(containsString("Add Motorhome")))
                .andExpect(status().isOk());



        mockMvc.perform(post("/createMotorhome")
                .param("licencePlate", motorhomes.get(0).getLicencePlate())
                .param("brand", motorhomes.get(0).getBrand())
                .param("model", motorhomes.get(0).getModel())
                .param("type", motorhomes.get(0).getType())
                .param("description", motorhomes.get(0).getDescription()))
                .andExpect(status().is(302));

        ArgumentCaptor<Motorhome> argsCap = ArgumentCaptor.forClass(Motorhome.class);
        Mockito.verify(motorhomeService).createMotorhome(argsCap.capture());

        assertEquals(argsCap.getValue().toString(),motorhomes.get(0).toString());
    }

    /**
     * @author Sverri
     * @throws Exception
     */
    @Test
    public void editTest() throws Exception {

        mockMvc.perform(get("/motorhomeIndex"))
                .andExpect(model().attribute("motorhomes", motorhomes))
                .andExpect(content().string(containsString("Motorhome Management")))
                .andExpect(content().string(containsString("brand1")))
                .andExpect(content().string(containsString("type5")))
                .andExpect(content().string(containsString("description10")))
                .andExpect(status().isOk());

    }

}
