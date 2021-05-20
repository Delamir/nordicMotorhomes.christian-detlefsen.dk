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
        extraList.add(new Extra(1, 200, "Table", "Picnic Table"));
        extraList.add(new Extra(1, 800, "Bike Rack", "Bike Rack"));

        ContractService contractService = new ContractService();
        Motorhome m = new Motorhome("AD99999", "TypeA", "BrandB", "ModelC",
                "A motorhome", "An ImagePath", 4000, true);
        Contract c = new Contract(1, Timestamp.valueOf("2021-05-09 12:20:20"), Timestamp.valueOf("2021-06-28 12:20:20"),
                200000, 2, 5000, "AD99999", extraList);

        assertEquals(contractService.calculatePrice(c, m), 5000);

        c.setUnderHalfFuelTank(true);
        c.setPickedUp(true);
        c.setExcessKm(200);
        c.setTransferKm(100);

        assertEquals(contractService.calculatePrice(c, m), 5340);
    }

    @Test
    void cancellationFee() {
    }
}