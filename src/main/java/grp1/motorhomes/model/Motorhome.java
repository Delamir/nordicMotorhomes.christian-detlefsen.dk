package grp1.motorhomes.model;


import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Patrick
 */
@Entity
public class Motorhome {

    @Id
    private String licencePlate;

    private String type;
    private String brand;
    private String model;
    private String description;
    private String imagePath;

    public Motorhome() {

    }

    public Motorhome(String licencePlate, String type, String brand, String model, String description, String imagePath) {

        this.licencePlate = licencePlate;
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.description = description;
        this.imagePath = imagePath;
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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}