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

        String selectModel = "SELECT count(*) FROM models WHERE model = ? and brand = ?";
        if (template.queryForObject(selectModel, Integer.class, motorhome.getModel(), motorhome.getBrand()) == 0) {
        String insertModel = "INSERT INTO models(model, brand) VALUES (?, ?)";
        template.update(insertModel, motorhome.getModel(),motorhome.getBrand());
        }

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