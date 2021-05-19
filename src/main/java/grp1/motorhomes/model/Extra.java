package grp1.motorhomes.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Christian
 */
@Entity
public class Extra {

    @Id
    private int extraId;
    private int price;
    private String description;
    private String name;

    /**
     * @author Christian
     */
    public Extra() {

    }

    /**
     * @author Christain
     * @param extraId
     * @param price
     * @param description
     * @param name
     */
    public Extra(int extraId, int price, String description, String name) {
        this.extraId = extraId;
        this.price = price;
        this.description = description;
        this.name = name;
    }

    public int getExtraId() {
        return extraId;
    }

    public void setExtraId(int extraId) {
        this.extraId = extraId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Extra{" +
                "extraId=" + extraId +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
