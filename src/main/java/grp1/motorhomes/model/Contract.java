package grp1.motorhomes.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

/**
 * @author Christian
 */
@Entity
public class Contract {

    @Id
    private int contractId;

    private LocalDate fromDate;
    private LocalDate toDate;
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
    public Contract (int contractId, LocalDate fromDate, LocalDate toDate, int odometer, int customerNumber, int price) {
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

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate from) {
        this.fromDate = from;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate to) {
        this.toDate = to;
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
