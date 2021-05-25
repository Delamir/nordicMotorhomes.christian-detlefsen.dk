package grp1.motorhomes.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * @author Christian
 */
@Entity
public class AutoService {
    @Id
    private int autoServiceId;

    private String serviceDescription;
    private boolean done;
    @OneToOne
    private Motorhome motorhome;

    /**
     * @author Christian
     */
    public AutoService() {

    }

    /**
     * @author Christian
     */
    public AutoService(int autoServiceId, String serviceDescription, boolean done, Motorhome motorhome) {
        this.autoServiceId = autoServiceId;
        this.serviceDescription = serviceDescription;
        this.done = done;
        this.motorhome = motorhome;
    }

    public int getAutoServiceId() {
        return autoServiceId;
    }

    public void setAutoServiceId(int autoServiceId) {
        this.autoServiceId = autoServiceId;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String autocheck) {
        this.serviceDescription = autocheck;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean checked) {
        this.done = checked;
    }

    public Motorhome getMotorhome() {
        return motorhome;
    }

    public void setMotorhomeWithParam(String licencePlate, String type, String brand, String model, String description, String imagePath, int price, boolean available) {
        this.motorhome = new Motorhome(licencePlate, type, brand, model, description, imagePath, price, available);
    }

    public void setMotorhome(String licencePlate) {
        this.motorhome = new Motorhome();
        this.motorhome.setLicencePlate(licencePlate);
    }

    @Override
    public String toString() {
        return "AutoService{" +
                "autoServiceId=" + autoServiceId +
                ", serviceDescription='" + serviceDescription + '\'' +
                ", done=" + done +
                '}';
    }
}
