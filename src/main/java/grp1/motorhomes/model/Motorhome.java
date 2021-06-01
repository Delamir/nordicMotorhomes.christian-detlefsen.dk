package grp1.motorhomes.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * @author Patrick and Sverrri
 */
@Entity
public class Motorhome {

    @Id
    private String licencePlate;

    // Transient means that we ignore this variable when interacting with the database
    // used when editing licencePlate
    @Transient
    private String previousLicencePlate;

    private String type;
    private String brand;
    private String model;
    private String description;
    private int price;
    private boolean available;

    /**
     * @author Sverri
     */
    public Motorhome() {
    }

    /**
     * @author Patrick
     */
    public Motorhome(String licencePlate, String type, String brand, String model, String description, int price, boolean available) {

        this.licencePlate = licencePlate;
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.description = description;
        this.price = price;
        this.available = available;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getPreviousLicencePlate() {
        return previousLicencePlate;
    }

    public void setPreviousLicencePlate(String previousLicencePlate) {
        this.previousLicencePlate = previousLicencePlate;
    }

    @Override
    public String toString() {
        return price + "€ per day, " + licencePlate + ", " + brand + " " + model + ", " + type;
    }
}