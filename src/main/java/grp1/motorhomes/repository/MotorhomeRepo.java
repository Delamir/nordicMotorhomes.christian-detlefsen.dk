package grp1.motorhomes.repository;

import grp1.motorhomes.model.Motorhome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Patrick
 */
@Repository
public class MotorhomeRepo {

    @Autowired
    JdbcTemplate template;

    /**
     * @author Patrick
     */
    public List<Motorhome> fetchAllMotorhomes() {
        String sql = "SELECT registration as licencePlate, type, brand, model, description FROM motorhomes JOIN models using(model_id);";
        RowMapper<Motorhome> rowMapper = new BeanPropertyRowMapper<>(Motorhome.class);
        return template.query(sql, rowMapper);
    }

    /**
     * @author Patrick
     */
    public void createMotorhome(Motorhome motorhome) {

        String insertModel = "INSERT INTO models(brand, model) SELECT ?, ? WHERE NOT EXISTS ( SELECT * FROM models WHERE brand = ? AND model = ?)";
        template.update(insertModel, motorhome.getBrand(),motorhome.getModel(),motorhome.getBrand(),motorhome.getModel());

        String insertMotorhome = "INSERT INTO motorhomes(registration, type, description, model_id) select ?, ?,? , " +
                "model_id FROM models WHERE brand = ? AND model = ?";
        template.update(insertMotorhome, motorhome.getLicencePlate(), motorhome.getType(), motorhome.getDescription(),
                motorhome.getBrand(), motorhome.getModel());
    }


    /**
     * @author Joachim
     */
    /*
    public Motorhome findMotorhomeById(int id) {
        String selectSql = "";
        RowMapper<Motorhome> rowMapper = new BeanPropertyRowMapper<>(Motorhome.class);
        return template.queryForObject(selectSql, rowMapper, id);
    }
*/
}