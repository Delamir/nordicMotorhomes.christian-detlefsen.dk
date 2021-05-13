package grp1.motorhomes.model;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Christian
 */

@Embeddable
public class Customer {


    private int customerNumber;

    private String name;

    private String licenceNumber;
    private String street;
    private String city;
    private int postCode;
    /**
     * @author Christian
     */
    public Customer() {

    }

    /**
     * @auhor Christian
     * @param customerNumber
     * @param name
     * @param licenceNumber
     * @param street
     * @param city
     * @param postCode
     */
    public Customer(int customerNumber, String name, String licenceNumber, String street, String city, int postCode) {
        this.customerNumber = customerNumber;
        this.name = name;
        this.licenceNumber = licenceNumber;
        this.street = street;
        this.city = city;
        this.postCode = postCode;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLicenceNumber() {
        return licenceNumber;
    }

    public void setLicenceNumber(String licenceNumber) {
        this.licenceNumber = licenceNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPostCode() {
        return postCode;
    }

    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerNumber=" + customerNumber +
                ", name='" + name + '\'' +
                ", licenceNumber='" + licenceNumber + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", postCode=" + postCode +
                '}';
    }
}
