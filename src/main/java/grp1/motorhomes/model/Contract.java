package grp1.motorhomes.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Christian
 */
@Entity
public class Contract {

    @Id
    private int contractId;

    private Timestamp fromDate;
    private Timestamp toDate;
    private int odometer;
    private int customerNumber;
    private int price;
    private String motorhome;

    /**
     * @author Christian
     */
    public Contract() {

    }

    /**
     * @author Christian
     * @param contractId
     * @param fromDate
     * @param toDate
     * @param odometer
     * @param customerNumber
     * @param price
     */
    public Contract (int contractId, Timestamp fromDate, Timestamp toDate, int odometer, int customerNumber,
                     int price, String motorhome) {
        this.contractId = contractId;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.odometer = odometer;
        this.customerNumber = customerNumber;
        this.price = price;
        this.motorhome = motorhome;
    }

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public Timestamp getFromDate() {
        return fromDate;
    }

    /**
     * @author Sverri
     * @param from
     *spring was not able to parse Timestamp on its own
     */
    public void setFromDate(String from) {
        LocalDateTime localDateTime;
        if(from.contains("T")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            localDateTime = LocalDateTime.parse(from, formatter);
        }else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
            localDateTime = LocalDateTime.parse(from, formatter);
        }
        this.toDate = Timestamp.valueOf(localDateTime);
    }
    public Timestamp getToDate() {
        return toDate;
    }

    /**
     * @author Sverri
     * @param to
     *spring was not able to parse Timstamp on its own
     */
    public void setToDate(String to) {
        LocalDateTime localDateTime;
        if(to.contains("T")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            localDateTime = LocalDateTime.parse(to, formatter);
        }else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
            localDateTime = LocalDateTime.parse(to, formatter);
        }
        this.toDate = Timestamp.valueOf(localDateTime);
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getMotorhome() {
        return motorhome;
    }

    public void setMotorhome(String motorhome) {
        this.motorhome = motorhome;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "contractId=" + contractId +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", odometer=" + odometer +
                ", customerNumber=" + customerNumber +
                ", price=" + price +
                ", motorhome='" + motorhome + '\'' +
                '}';
    }
}
