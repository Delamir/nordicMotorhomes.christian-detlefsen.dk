package grp1.motorhomes.repository;

import grp1.motorhomes.model.AutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author Christian
 */
@Repository
public class AutoServiceRepo {

    @Autowired
    JdbcTemplate template;

    /**
     * @author Christian
     */
    public List<AutoService> fetchAllAutoServices() {
        String sqlStatement = "SELECT * FROM autoservices " +
                "JOIN motorhomes ON autoservices.motorhome = motorhomes.registration " +
                "JOIN models using(model_id) WHERE done = false";

        AutoServiceResultSetExtractor extractor = new AutoServiceResultSetExtractor();

        return (List<AutoService>) template.query(sqlStatement, extractor);
    }

    /**
     * @author Joachim
     */
    public void createAutoService(AutoService autoservice) {
        String insertAutoServiceValues = "INSERT INTO autoservices (service_description, motorhome, done) VALUES (?, ?, ?)";

        template.update(insertAutoServiceValues, autoservice.getServiceDescription(),
                autoservice.getMotorhome().getLicencePlate(), autoservice.isDone());
    }

    /**
     * @author Joachim
     */
    public void editAutoService(AutoService autoService) {
        String updateSql = "UPDATE autoservices SET service_description = ?, motorhome = ?, done = ? WHERE autoservice_id = ?";

        template.update(updateSql, autoService.getServiceDescription(), autoService.getMotorhome(), autoService.isDone());
    }

    /**
     * @author Christian
     */
    public void markDone(AutoService autoService) {
        String updateSql = "UPDATE autoservices SET done = true WHERE autoservice_id = ?";

        template.update(updateSql, autoService.getAutoServiceId());
    }
}
