package grp1.motorhomes.model;

import grp1.motorhomes.repository.MotorhomeRepo;

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
    private Motorhome;

    /**
     * @author Christian
     */
    public
}
