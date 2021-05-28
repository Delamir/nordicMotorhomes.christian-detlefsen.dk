package grp1.motorhomes.repository;

import grp1.motorhomes.model.AutoService;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class AutoServiceResultSetExtractor implements ResultSetExtractor {

    /**
     * the method that is run when the class tries to map data from a resultSet from the JdbcTemplate
     * @param resultSet
     * @return
     * @throws SQLException
     * @throws DataAccessException
     */
    @Override
    public List<AutoService> extractData(ResultSet resultSet) throws SQLException, DataAccessException {

        // we use hash map to make sure there is only one object of an auto service with a given auto service id
        HashMap<Integer, AutoService> autoServiceHashMap = new HashMap<>();

        // we run trough all the rows of the query
        while(resultSet.next()) {

            // we see if the auto service is already in the hash map
            AutoService autoService = autoServiceHashMap.get(resultSet.getInt("autoservice_id"));

            // if it is not we make a new autoService object and put it in the hash map
            if (autoService == null) {

                autoService = new AutoService();
                autoService.setAutoServiceId(resultSet.getInt("autoservice_id"));
                autoService.setServiceDescription(resultSet.getString("service_description").toString());
                autoService.setMotorhomeWithParam(resultSet.getString("motorhome"),
                        resultSet.getString("type"), resultSet.getString("brand"),
                        resultSet.getString("model"), resultSet.getString("description"),
                        resultSet.getInt("price"), resultSet.getBoolean("available"));
                autoService.setDone(resultSet.getBoolean("done"));

                autoServiceHashMap.put(autoService.getAutoServiceId(), autoService);

            }
        }
        return new ArrayList<>(autoServiceHashMap.values());
    }
}

