package grp1.motorhomes.repository;

import grp1.motorhomes.model.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Christian
 */
@Repository
public class ContractRepo {

    @Autowired
    JdbcTemplate template;

    /**
     * @author Christian
     * @return
     */
    public List<Contract> fetchAllContracts() {
        String sqlStatement = "";
        RowMapper<Contract> rowMapper = new BeanPropertyRowMapper<>(Contract.class);
        return template.query(sqlStatement, rowMapper);
    }

    /**
     * @author Christian
     * @param contract
     */
    public void createRentalContract(Contract contract) {
        String insert = "";
    }
}
