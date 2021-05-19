package grp1.motorhomes.repository;

import grp1.motorhomes.model.Contract;
import grp1.motorhomes.model.Extra;
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
     * @return
     * @author Christian
     */
    public List<Contract> fetchAllContracts() {
        String sqlStatement = "SELECT contract_id AS contractId, from_date AS fromDate, to_date AS toDate, " +
                "odometer, excess_km AS excessKm, transfer_km AS transferKm, customer_number AS customer, customer_number AS customerNumber, " +
                "motorhome,  extra_id AS extraId " +
                "FROM contracts JOIN contracts_extras using(contract_id)";
        RowMapper<Contract> rowMapper = new BeanPropertyRowMapper<>(Contract.class);
        return template.query(sqlStatement, rowMapper);
    }

    public List<Extra> fetchAllExtras() {
        String sqlStatement = "SELECT extra_id AS extraId, price, description, name " +
                "FROM extras";
        RowMapper<Extra> rowMapper = new BeanPropertyRowMapper<>(Extra.class);
        return template.query(sqlStatement,rowMapper);
    }

    /**
     * @param contract
     * @author Christian
     */
    public void createContract(Contract contract) {
        String insertContractValues = "INSERT INTO contracts (from_date, to_date, odometer, customer_number, motorhome) "
                + "VALUES (?, ?, ?, ?, ?)";
        template.update(insertContractValues, contract.getFromDate(), contract.getToDate(),
                contract.getOdometer(), contract.getCustomerNumber(), contract.getMotorhome());
    }

    /**
     * @author Sverri
     * @param contractId
     * @return
     */
    public Contract findContract(int contractId) {
        String selectSql = "SELECT contract_id AS contractId, from_date AS fromDate, to_date AS toDate," +
                "odometer, customer_number AS customer, customer_number AS customerNumber, motorhome " +
                "FROM contracts WHERE contract_id = ?";
        RowMapper<Contract> rowMapper = new BeanPropertyRowMapper<>(Contract.class);
        return template.queryForObject(selectSql, rowMapper, contractId);
    }

    /**
     * @author Sverri
     * @param contract
     */
    public void editContract(Contract contract) {
        String updateSql = "UPDATE contracts SET from_date = ?, to_date = ?, odometer = ?, customer_number = ?, motorhome = ? WHERE contract_id = ?";
        template.update(updateSql,contract.getFromDate(),contract.getToDate(),contract.getOdometer(), contract.getCustomerNumber(), contract.getMotorhome(), contract.getContractId());
    }

    /**
     * @author Sverri
     * @param contractId
     */
    public void deleteContract(int contractId){
        String deleteSql = "DELETE FROM contracts WHERE contract_id = ?";
        template.update(deleteSql,contractId);
    }

}
