package grp1.motorhomes.repository;

import grp1.motorhomes.model.AutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
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
        String sqlStatement = "SELECT autoservice_id AS autoServiceId, autocheck, motorhome, checked " +
                "FROM autoservices JOIN motorhomes ON autoservices.motorhome = motorhomes.registration WHERE checked = false";

        ContractResultSetExtractor extractor = new ContractResultSetExtractor();

        return (List<AutoService>) template.query(sqlStatement, extractor);
    }

    /**
     * @author Joachim
     */
    public void createAutoService(AutoService autoservice) {
        String insertAutoServiceValues = "INSERT INTO autoservices (autocheck, motorhome, checked) VALUES (?, ?, ?)";

        KeyHolder keyholder = new GeneratedKeyHolder();

        template.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(insertAutoServiceValues, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, autoservice.getAutocheck());
            preparedStatement.setString(2, autoservice.getMotorhome().getLicencePlate());
            preparedStatement.setBoolean(3, autoservice.isChecked());
            return preparedStatement;
        }, keyholder);
    }

    /**
     * @author Joachim
     */
    public void editAutoService(AutoService autoService) {
        String updateSql = "UPDATE autoservices SET autocheck = ?, motorhome = ?, checked = ? WHERE autoservice_id = ?";

        template.update(updateSql, autoService.getAutocheck(), autoService.getMotorhome(), autoService.isChecked());
    }
}
