package grp1.motorhomes.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
    private int excessKm;
    private int transferKm;
    private String deliveryPoint;
    private String pickupPoint;

    @ManyToMany
    private List<Extra> extras;

    private boolean underHalfFuelTank;
    private boolean delivered;
    private boolean pickedUp;
    private boolean closed;
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
     * @param extras
     */
    public Contract(int contractId, Timestamp fromDate, Timestamp toDate, int odometer, int customerNumber,
                    int price, String motorhome, int excessKm, int transferKm, List<Extra> extras) {
        this.contractId = contractId;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.odometer = odometer;
        this.customerNumber = customerNumber;
        this.price = price;
        this.motorhome = motorhome;
        this.excessKm = excessKm;
        this.transferKm = transferKm;
        this.extras = extras;
    }

    public void addExtra(int id, int price, String name, String description){
        if(extras == null)
            extras = new ArrayList<>();
        extras.add(new Extra(id, price, name, description));
    }

    public List<Extra> getExtras() {
        return extras;
    }

    public void setExtras(List<Extra> extras) {
        this.extras = extras;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    public boolean isPickedUp() {
        return pickedUp;
    }

    public void setPickedUp(boolean pickedUp) {
        this.pickedUp = pickedUp;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
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

    public String getFromDateAsString(){
        String returnString = "";
        returnString += fromDate.toLocalDateTime().getDayOfMonth()+". ";
        returnString += fromDate.toLocalDateTime().getMonth().name().toLowerCase() + " ";
        returnString += fromDate.toLocalDateTime().getHour() + ":" + fromDate.toLocalDateTime().getMinute();
        return returnString;
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
        this.fromDate = Timestamp.valueOf(localDateTime);
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

    public int getExcessKm() {
        return excessKm;
    }

    public void setExcessKm(int excessKm) {
        this.excessKm = excessKm;
    }

    public int getTransferKm() {
        return transferKm;
    }

    public void setTransferKm(int transferKm) {
        this.transferKm = transferKm;
    }

    public boolean isUnderHalfFuelTank() {
        return underHalfFuelTank;
    }

    public void setUnderHalfFuelTank(boolean underHalfFuelTank) {
        this.underHalfFuelTank = underHalfFuelTank;
    }

    public String getDeliveryPoint() {
        return deliveryPoint;
    }

    public void setDeliveryPoint(String deliveryPoint) {
        this.deliveryPoint = deliveryPoint;
    }

    public String getPickupPoint() {
        return pickupPoint;
    }

    public void setPickupPoint(String pickupPoint) {
        this.pickupPoint = pickupPoint;
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
                ", excessKm=" + excessKm +
                ", transferKm=" + transferKm +
                ", extras=" + extras +
                ", delivered=" + delivered +
                ", pickedUp=" + pickedUp +
                ", closed=" + closed +
                '}';
    }
}
