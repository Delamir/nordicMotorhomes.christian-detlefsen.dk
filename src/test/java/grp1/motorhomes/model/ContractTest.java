package grp1.motorhomes.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Christian
 */
public class ContractTest {

    /**
     * @author Christian
     */
    @Test
    public void setFromDateTest() {
        Contract c = new Contract();

            c.setFromDate("2021-05-10T18:29");
            assertEquals(LocalDateTime.of(2021, 5, 10, 18, 29), c.getFromDate());

    }

    /**
     * @author Christian
     */
    @Test
    public void setToDateTest() {
        Contract c = new Contract();

            c.setToDate("2021-05-10T18:29");
            assertEquals(LocalDateTime.of(2021, 5, 10, 18, 29), c.getToDate());
    }

    /**
     * @author Christian
     */
    @Test
    void addExtra() {
        Contract c = new Contract();
        Extra e = new Extra(1, 2, "Extra 1", "Description212231");

        c.addExtra(e.getExtraId(), e.getPrice(), e.getName(), e.getDescription());

        assertEquals("Extra 1, Description212231, 2€ per day", c.getExtras().get(0).toString());
    }

    /**
     * @author Patrick
     */
    @Test
    void getFromDateAsString() {
        Contract c = new Contract();

        c.setFromDate("2021-05-10 18:29:00.5");
        assertEquals("10. may 18:29", c.getFromDateAsString());
    }


    /**
     * @author Patrick
     */
    @Test
    void getToDateAsString() {
        Contract c = new Contract();

        c.setToDate("2021-05-10T18:29");
        assertEquals("10. may 18:29", c.getToDateAsString());
    }

    /**
     * @author Patrick
     */
    @Test
    void getFinalPriceAsString() {
        Contract c = new Contract();

        c.setFinalPrice(42.4343434343434343);
        assertTrue(c.getFinalPriceAsString().matches("47[,.]43"));
        assertEquals("42.43", c.getFinalPriceAsString());
    }

    /**
     * @author Christian
     */
    @Test
    void setMotorhomeParams() {

        Contract contract = new Contract();

        contract.setMotorhomeParams("LicencePlate", "Type 1", "Brand 1", "Model 1",
                "Description 12345", 10,true);

        assertEquals(new Motorhome("LicencePlate", "Type 1", "Brand 1", "Model 1",
                "Description 12345", 10,true).toString(), contract.getMotorhome().toString());
    }

    /**
     * @author Christian
     */
    @Test
    void setCustomerParams() {

        Contract contract = new Contract();

        contract.setCustomerParams(1, "Sverri", "22222222", "Vejen 12",
                "Bagsværd", 2880);

        assertEquals(new Customer(1, "Sverri", "22222222", "Vejen 12",
                "Bagsværd", 2880).toString(), contract.getCustomer().toString());
    }
}
