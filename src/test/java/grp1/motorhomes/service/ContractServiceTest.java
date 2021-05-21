package grp1.motorhomes.service;

import grp1.motorhomes.model.Contract;
import grp1.motorhomes.model.Extra;
import grp1.motorhomes.model.Motorhome;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Christian
 */
class ContractServiceTest {

    /**
     * @author Christian
     */
    @Test
    void calculatePrice() {

        ArrayList<Extra> extraList = new ArrayList<>();
        extraList.add(new Extra(1, 20, "Table", "Picnic Table"));
        extraList.add(new Extra(1, 80, "Bike Rack", "Bike Rack"));

        ContractService contractService = new ContractService();
        Motorhome m = new Motorhome("AD99999", "TypeA", "BrandB", "ModelC",
                "A motorhome", "An ImagePath", 200, true);
        Contract c = new Contract(1, Timestamp.valueOf("2021-05-09 12:20:20"), Timestamp.valueOf("2021-05-19 12:20:20"),
                200000, 2, "AD99999", extraList);
        Contract c2 = new Contract(1, Timestamp.valueOf("2021-05-09 13:45:42"), Timestamp.valueOf("2021-05-19 12:20:20"),
                200000, 2, "AD99999", extraList);
        Contract c3 = new Contract(1, Timestamp.valueOf("2021-05-09 11:10:20"), Timestamp.valueOf("2021-05-19 12:20:20"),
                200000, 2, "AD99999", extraList);

        assertEquals(3000, contractService.calculatePrice(c, m.getPrice()));

        c.setUnderHalfFuelTank(true);
        c.setPickedUp(true);
        c.setExcessKm(200);
        c.setTransferKm(100);

        assertEquals(3340, contractService.calculatePrice(c, m.getPrice()));

        assertEquals(3000, contractService.calculatePrice(c2, m.getPrice()));
        assertEquals(3000, contractService.calculatePrice(c3, m.getPrice()));
    }

    @Test
    void cancellationFee() {

        ArrayList<Extra> extraList = new ArrayList<>();
        extraList.add(new Extra(1, 200, "Table", "Picnic Table"));
        extraList.add(new Extra(1, 800, "Bike Rack", "Bike Rack"));

        ContractService contractService = new ContractService();
        Motorhome m = new Motorhome("AD99999", "TypeA", "BrandB", "ModelC",
                "A motorhome", "An ImagePath", 4000, true);
        Contract c50 = new Contract(1, Timestamp.valueOf("2021-05-09 12:20:20"), Timestamp.valueOf("2021-05-19 12:20:20"),
                200000, 2, "AD99999", extraList);
        Contract cBetween49And15 = new Contract(1, Timestamp.valueOf("2021-05-09 13:45:42"), Timestamp.valueOf("2021-05-19 12:20:20"),
                200000, 2, "AD99999", extraList);
        Contract cUnder15 = new Contract(1, Timestamp.valueOf("2021-05-09 11:10:20"), Timestamp.valueOf("2021-05-19 12:20:20"),
                200000, 2, "AD99999", extraList);
        Contract cSameDay = new Contract(1, Timestamp.valueOf("2021-05-09 11:10:20"), Timestamp.valueOf("2021-05-19 12:20:20"),
                200000, 2, "AD99999", extraList);



    }
}