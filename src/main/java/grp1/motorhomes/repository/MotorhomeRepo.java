package grp1.motorhomes.repository;

import grp1.motorhomes.model.Motorhome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MotorhomeRepo {

    @Autowired
    JdbcTemplate template;

    public List<Motorhome> fetchAllMotorhomes() {
        String sql = "SELECT registration as licencePlate, type, brand, model FROM motorhomes JOIN models using(model_id);";
        RowMapper<Motorhome> rowMapper = new BeanPropertyRowMapper<>(Motorhome.class);
        return template.query(sql, rowMapper);
    }

    public void createMotorhome(Motorhome motorhome) {

        String insertModel = "INSERT INTO models(model, brand) VALUES (?, ?)";
        template.update(insertModel, motorhome.getModel(),motorhome.getBrand());

        String insertMotorhome = "INSERT INTO motorhomes(registration, type, description, model_id) select ?, ?,? ,model_id FROM models WHERE brand = 'Fiat' AND model = '500'";
        template.update(insertMotorhome, motorhome.getLicencePlate(), motorhome.getType(), motorhome.getDescription());
    }

    /*
    public Motorhome findMotorhomeById(int id) {
        String selectSql = "";
        RowMapper<Motorhome> rowMapper = new BeanPropertyRowMapper<>(Motorhome.class);
        return template.queryForObject(selectSql, rowMapper, id);
    }

    public addMotorhome(Motorhome motorhome) {

    }
*/
}