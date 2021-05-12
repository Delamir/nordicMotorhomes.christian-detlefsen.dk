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

    private LocalDate from;

    private LocalDate to;
    private int odometer;
    private Customer customer;
    private int price;

    /**
     * @author Christian
     */
    public Contract() {

    }

    /**
     * @author Christian
     * @param contractId
     * @param from
     * @param to
     * @param odometer
     * @param customer
     * @param price
     */
    public Contract (int contractId, LocalDate from, LocalDate to, int odometer, Customer customer, int price) {
        this.contractId = contractId;
        this.from = from;
        this.to = to;
        this.odometer = odometer;
        this.customer = customer;
        this.price = price;
    }

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public LocalDate getFrom() {
        return from;
    }

    public void setFrom(LocalDate from) {
        this.from = from;
    }

    public LocalDate getTo() {
        return to;
    }

    public void setTo(LocalDate to) {
        this.to = to;
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
