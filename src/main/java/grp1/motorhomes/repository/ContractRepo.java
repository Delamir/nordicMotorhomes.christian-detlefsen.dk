package grp1.motorhomes.repository;

import grp1.motorhomes.model.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
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
                "odometer, price, customer_number AS customer, customer_number AS customerNumber, motorhome " +
                "FROM contracts ";
        RowMapper<Contract> rowMapper = new BeanPropertyRowMapper<>(Contract.class);
        return template.query(sqlStatement, rowMapper);
    }

    /**
     * @param contract
     * @author Christian
     */
    public void createContract(Contract contract) {
        String insertContractValues = "INSERT INTO contracts (from_date, to_date, odometer, price, customer_number, motorhome) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        template.update(insertContractValues, contract.getFromDate(), contract.getToDate(),
                contract.getOdometer(), contract.getPrice(), contract.getCustomerNumber(), contract.getMotorhome());
    }

    public Contract findContract(int contractId) {
        String selectSql = "SELECT contract_id AS contractId, from_date AS fromDate, to_date AS toDate," +
                "odometer, price, customer_number AS customer, customer_number AS customerNumber, motorhome " +
                "FROM contracts WHERE contract_id = ?";
        RowMapper<Contract> rowMapper = new BeanPropertyRowMapper<>(Contract.class);
        return template.queryForObject(selectSql, rowMapper, contractId);
    }

    public void editContract(Contract contract) {
        String updateSql = "UPDATE contracts SET from_date = ?, to_date = ?, odometer = ?, price = ?, customer_number = ?, motorhome = ? WHERE contract_id = ?";
        template.update(updateSql,contract.getFromDate(),contract.getToDate(),contract.getOdometer(),contract.getPrice(),contract.getCustomerNumber(), contract.getMotorhome(), contract.getContractId());
    }
    public void deleteContract(int contractId){
        String deleteSql = "DELETE FROM contracts WHERE contract_id = ?";
        template.update(deleteSql,contractId);
    }

}
