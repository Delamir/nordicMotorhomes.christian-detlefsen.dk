package grp1.motorhomes.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Christian
 */
@Entity
public class Contract {

    @Id
    private int contractId;

    private LocalDateTime fromDate;
    private LocalDateTime toDate;
    private int odometer;
    private int customerNumber;
    private int price;

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
    public Contract (int contractId, LocalDateTime fromDate, LocalDateTime toDate, int odometer, int customerNumber, int price) {
        this.contractId = contractId;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.odometer = odometer;
        this.customerNumber = customerNumber;
        this.price = price;
    }

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDateTime from) {
        this.fromDate = from;
    }

    public LocalDateTime getToDate() {
        return toDate;
    }

    public void setToDate(String to) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.toDate = LocalDateTime.parse(to, formatter);

    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public int getCustomer() {
        return customerNumber;
    }

    public void setCustomer(int customerNumber) {
        this.customerNumber = customerNumber;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "contractId=" + contractId +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", odometer=" + odometer +
                ", customer=" + customerNumber +
                ", price=" + price +
                '}';
    }
}
