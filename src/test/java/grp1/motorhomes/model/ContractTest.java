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


    @Test
    void addExtra() {
    }

    @Test
    void getFromDateAsString() {
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
