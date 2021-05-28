package grp1.motorhomes.repository;

import grp1.motorhomes.model.Constants;
import grp1.motorhomes.model.Motorhome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Patrick and Sverri
 */
@Repository
public class MotorhomeRepo {

    @Autowired
    JdbcTemplate template;

    /**
     * @author Patrick
     */
    public List<Motorhome> fetchAllMotorhomes() {
        String sql = "SELECT registration as licencePlate, type, brand, model, description, price, available FROM motorhomes JOIN models using(model_id) ORDER BY registration;";
        RowMapper<Motorhome> rowMapper = new BeanPropertyRowMapper<>(Motorhome.class);
        return template.query(sql, rowMapper);
    }

    /**
     * @author Christian
     */
    public List<Motorhome> fetchAllUnavailableMotorhomes() {
        // we select all motorhomes that are set as unavailable and do not have any autoservices registered
        String sqlStatement = "SELECT registration as licencePlate, type, brand, model, description, price, available " +
                "FROM motorhomes JOIN models using(model_id) LEFT JOIN autoservices a on motorhomes.registration = a.motorhome WHERE available = false AND autoservice_id is null";
        RowMapper<Motorhome> rowMapper = new BeanPropertyRowMapper<>(Motorhome.class);
        return template.query(sqlStatement, rowMapper);
    }

    /**
     * @author Sverri
     */
    public List<Motorhome> fetchMotorhomesBetween(LocalDateTime from, LocalDateTime to) {
        // we select all motorhomes that are not on contracts between the given dates and do not have any autoservices registered
        String sql = "SELECT registration as licencePlate, type, brand, model, description, price, available from motorhomes " +
                "LEFT JOIN contracts ON registration = motorhome " +
                "JOIN models using(model_id) " +
                "LEFT JOIN autoservices a on motorhomes.registration = a.motorhome " +
                "WHERE (autoservice_id is null OR done = true) AND ((from_Date NOT BETWEEN ? AND ?) AND (to_Date NOT BETWEEN ? AND ?))  OR from_date is null OR to_date is null ORDER BY type";
        RowMapper<Motorhome> rowMapper = new BeanPropertyRowMapper<>(Motorhome.class);
        return template.query(sql, rowMapper, from.toLocalDate(), to.plusDays(Constants.GRACE_PERIOD), from.toLocalDate(), to.plusDays(2));
    }

    /**
     * @param motorhome
     * @author Patrick
     */
    public void createMotorhome(Motorhome motorhome) throws Exception {

        String insertModel = "INSERT INTO models(brand, model, type) SELECT ?, ?, ? WHERE NOT EXISTS " +
                "( SELECT * FROM models WHERE brand = ? AND model = ? AND type = ?)";
        template.update(insertModel, motorhome.getBrand(), motorhome.getModel(), motorhome.getType(),
                motorhome.getBrand(), motorhome.getModel(), motorhome.getType());

        String insertMotorhome = "INSERT INTO motorhomes(registration, description, price, model_id) select ?, ?, ?," +
                "model_id FROM models WHERE brand = ? AND model = ? AND type = ?";
        template.update(insertMotorhome, motorhome.getLicencePlate(), motorhome.getDescription(), motorhome.getPrice(),
                motorhome.getBrand(), motorhome.getModel(), motorhome.getType());
    }

    /**
     * @param licencePlate
     * @author Patrick og Sverri
     */

    public Motorhome findMotorhome(String licencePlate) {
        String selectSql = "SELECT registration as licencePlate, type, brand, model, description, price, available FROM motorhomes" +
                " JOIN models using(model_id) WHERE registration = ?";
        RowMapper<Motorhome> rowMapper = new BeanPropertyRowMapper<>(Motorhome.class);
        return template.queryForObject(selectSql, rowMapper, licencePlate);
    }

    /**
     * @param motorhome
     * @author Sverri
     */
    public void editMotorhome(Motorhome motorhome) {

        // if model was edited get modelId and insert new model if needed
        int modelId;
        try { //try and find the model
            String modelSelect = "SELECT model_id FROM models WHERE brand = ? AND model = ? AND type = ?";
            modelId = template.queryForObject(modelSelect, Integer.class, motorhome.getBrand(), motorhome.getModel(), motorhome.getType());
            System.out.println(modelId);
        } catch (EmptyResultDataAccessException e) { //if model not found insert
            String insertModel = "INSERT INTO models(brand, model, type) VALUES(?, ?, ?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();

            // jdbc template does not by default support returning generated keys
            // code stub with much help from https://www.baeldung.com/spring-jdbc-autogenerated-keys
            template.update(connection -> {
                PreparedStatement preparedStatement = connection.prepareStatement(insertModel, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, motorhome.getBrand());
                preparedStatement.setString(2, motorhome.getModel());
                preparedStatement.setString(3, motorhome.getType());
                return preparedStatement;
            }, keyHolder);

            modelId = keyHolder.getKey().intValue();
        }

        //update the motorhome
        String sqlUpdate = "UPDATE motorhomes SET registration = ?, description = ?, model_id = ?, price = ?, available = ? WHERE registration = ?";
        template.update(sqlUpdate, motorhome.getLicencePlate(), motorhome.getDescription(),
                modelId, motorhome.getPrice(), motorhome.isAvailable(), motorhome.getPreviousLicencePlate());
    }

    /**
     * @param licencePlate
     * @author Patrick
     */
    public void deleteMotorhome(String licencePlate) {
        String deleteSql = "DELETE FROM autoservices WHERE motorhome = ?";
        template.update(deleteSql, licencePlate);

        deleteSql = "DELETE FROM motorhomes WHERE registration = ? ";
        template.update(deleteSql, licencePlate);
    }

    /**
     * @param licencePlate
     * @param status
     * @author Christian
     */
    public void setAvailable(String licencePlate, boolean status) {
        String setAvailableSql = "UPDATE motorhomes SET available = ? WHERE registration = ?";
        template.update(setAvailableSql, status, licencePlate);
    }
}