package grp1.motorhomes.repository;

import grp1.motorhomes.model.Extra;
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
public class ExtraRepo {

    @Autowired
    JdbcTemplate template;

    public List<Extra> fetchAllExtras() {
        String sql = "SELECT extra_id AS extraId, price, description, name FROM extras";
        RowMapper<Extra> rowMapper = new BeanPropertyRowMapper<>(Extra.class);
        return template.query(sql, rowMapper);
    }

    public void createExtra(Extra extra) {

        String insertExtra = "INSERT INTO extras(extra_id, price, description, name) SELECT ?, ?, ?, ?";
        template.update(insertExtra, extra.getExtraId(), extra.getPrice(), extra.getDescription(), extra.getName());
    }

    public Extra findExtra(int extraId) {
        String selectSql = "SELECT extra_id as extraId, price, description, name FROM extras WHERE extra_id = ?";
        RowMapper<Extra> rowMapper = new BeanPropertyRowMapper<>(Extra.class);
        return template.queryForObject(selectSql, rowMapper, extraId);
    }

    public void editExtra(Extra extra) {
        String updateSql = "UPDATE extras SET price = ?, description = ?, name = ? WHERE extra_id = ?";
        template.update(updateSql, extra.getPrice(), extra.getDescription(), extra.getName(), extra.getExtraId());
    }

    public void deleteExtra(int extraId) {
        String deleteSql = "DELETE FROM extras WHERE extra_id = ?";
        template.update(deleteSql, extraId);
    }

}
