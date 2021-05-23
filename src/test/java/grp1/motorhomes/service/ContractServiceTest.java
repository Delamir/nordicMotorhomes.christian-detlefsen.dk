package grp1.motorhomes.service;

import grp1.motorhomes.model.Contract;
import grp1.motorhomes.model.Customer;
import grp1.motorhomes.model.Extra;
import grp1.motorhomes.model.Motorhome;
import org.junit.jupiter.api.Test;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
        ContractService contractService = new ContractService();

        ArrayList<Extra> extraList = new ArrayList<>();
        extraList.add(new Extra(1, 20, "Table", "Picnic Table"));
        extraList.add(new Extra(1, 80, "Bike Rack", "Bike Rack"));


        Motorhome motorhome = new Motorhome("AD99999", "TypeA", "BrandB", "ModelC",
                "A motorhome", "An ImagePath", 200, true);

        Customer customer = new Customer(1, "Bent", "KY768IO", "Vejen 12", "Byen", 2000);

        Contract contract = new Contract(1, Timestamp.valueOf("2021-05-09 12:20:20"), Timestamp.valueOf("2021-05-19 12:20:20"),
                200000, customer, motorhome, extraList);
        Contract contract1 = new Contract(1, Timestamp.valueOf("2021-05-09 13:45:42"), Timestamp.valueOf("2021-05-19 12:20:20"),
                200000, customer, motorhome, extraList);
        Contract contract2 = new Contract(1, Timestamp.valueOf("2021-05-09 11:10:20"), Timestamp.valueOf("2021-05-19 12:20:20"),
                200000, customer, motorhome, extraList);

        assertEquals(3000, contractService.calculatePrice(contract, motorhome.getPrice()));

        contract.setUnderHalfFuelTank(true);
        contract.setPickedUp(true);
        contract.setExcessKm(200);
        contract.setTransferKm(100);

        assertEquals(3340, contractService.calculatePrice(contract, motorhome.getPrice()));
        assertEquals(3000, contractService.calculatePrice(contract1, motorhome.getPrice()));
        assertEquals(3000, contractService.calculatePrice(contract2, motorhome.getPrice()));
    }

    /**
     * @author Patrick og Christian
     */
    @Test
    void cancellationFee() {
        ContractService contractService = new ContractService();

        LocalDateTime today = LocalDateTime.now();

        ArrayList<Extra> extraList = new ArrayList<>();
        extraList.add(new Extra(1, 2, "Table", "Picnic Table"));
        extraList.add(new Extra(1, 8, "Bike Rack", "Bike Rack"));


        Motorhome motorhome = new Motorhome("AD99999", "TypeA", "BrandB", "ModelC",
                "A motorhome", "An ImagePath", 100, true);
        Customer customer = new Customer(1, "Bent", "KY768IO", "Vejen 12", "Byen", 2000);

        Contract cOver50 = new Contract(1, Timestamp.valueOf(today.plusDays(60)), Timestamp.valueOf(today.plusDays(90)),
                200000, customer, motorhome, extraList);
        Contract cBetween49And15 = new Contract(1, Timestamp.valueOf(today.plusDays(30)), Timestamp.valueOf(today.plusDays(60)),
                200000, customer, motorhome, extraList);
        Contract cUnder15 = new Contract(1, Timestamp.valueOf(today.plusDays(10)), Timestamp.valueOf(today.plusDays(40)),
                200000, customer, motorhome, extraList);
        Contract cSameDay = new Contract(1, Timestamp.valueOf(today), Timestamp.valueOf(today.plusDays(30)),
                200000, customer, motorhome, extraList);
        Contract cAfterStart = new Contract(1, Timestamp.valueOf(today.minusDays(2)), Timestamp.valueOf(today.plusDays(28)),
                200000, customer, motorhome, extraList);

        assertEquals(660, contractService.cancellationFee(cOver50, motorhome.getPrice()));
        assertEquals(1650, contractService.cancellationFee(cBetween49And15, motorhome.getPrice()));
        assertEquals(2640, contractService.cancellationFee(cUnder15, motorhome.getPrice()));
        assertEquals(3135, contractService.cancellationFee(cSameDay, motorhome.getPrice()));
        assertEquals(3300, contractService.cancellationFee(cAfterStart, motorhome.getPrice()));
    }
}