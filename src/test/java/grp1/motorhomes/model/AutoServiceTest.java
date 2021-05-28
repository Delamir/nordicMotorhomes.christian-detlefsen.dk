package grp1.motorhomes.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AutoServiceTest {

    @Test
    void setMotorhomeWithParam() {

        AutoService autoService = new AutoService();

        autoService.setMotorhomeWithParam("LicencePlate", "Type 1", "Brand 1", "Model 1",
                "Description 12345", 10,true);

        assertEquals(new Motorhome("LicencePlate", "Type 1", "Brand 1", "Model 1",
                "Description 12345", 10,true).toString(), autoService.getMotorhome().toString());
    }
}