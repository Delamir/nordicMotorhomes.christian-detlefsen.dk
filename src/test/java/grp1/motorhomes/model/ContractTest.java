package grp1.motorhomes.model;

import org.junit.jupiter.api.Test;

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
            assertEquals("2021-05-10 18:29:00.0", c.getFromDate().toString());

        }

        /**
         * @author Christian
         */
        @Test
        public void setToDateTest() {
            Contract c = new Contract();

            c.setToDate("2021-05-10T18:29");
            assertEquals("2021-05-10 18:29:00.0", c.getToDate().toString());

        }
    }
