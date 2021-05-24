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

    private String autocheck;
    private boolean checked;
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
    public AutoService(int autoServiceId, String autocheck, boolean checked, Motorhome motorhome) {
        this.autoServiceId = autoServiceId;
        this.autocheck = autocheck;
        this.checked = checked;
        this.motorhome = motorhome;
    }

    public int getAutoServiceId() {
        return autoServiceId;
    }

    public void setAutoServiceId(int autoServiceId) {
        this.autoServiceId = autoServiceId;
    }

    public String getAutocheck() {
        return autocheck;
    }

    public void setAutocheck(String autocheck) {
        this.autocheck = autocheck;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Motorhome getMotorhome() {
        return motorhome;
    }

    public void setMotorhome(String licencePlate, String type, String brand, String model, String description, String imagePath, int price, boolean available) {
        this.motorhome = new Motorhome(licencePlate, type, brand, model, description, imagePath, price, available);
    }
}
