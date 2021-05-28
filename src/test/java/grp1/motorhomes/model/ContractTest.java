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
        assertEquals("42,43", c.getFinalPriceAsString());

    }

    @Test
    void setMotorhomeParams() {
    }

    @Test
    void setCustomerParams() {
    }
}
