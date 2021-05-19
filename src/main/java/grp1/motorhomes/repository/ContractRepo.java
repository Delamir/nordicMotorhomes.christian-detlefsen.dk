package grp1.motorhomes.repository;

import grp1.motorhomes.model.Contract;
import grp1.motorhomes.model.Extra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
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
        String sqlStatement = "SELECT contract_id, from_date, to_date, " +
                "odometer, excess_km, transfer_km, customer_number, customer_number, " +
                "motorhome, delivery_point, delivered, pickup_point, picked_up, closed, extra_id, price, name, description " +
                "FROM contracts JOIN contracts_extras using(contract_id) JOIN extras using(extra_id)";
        ResultSetExtractor extractor = new ContractResultSetExtractor();
        return (List<Contract>) template.query(sqlStatement, extractor);
    }

    /**
     * @param contract
     * @author Christian
     */
    public void createContract(Contract contract) {
        String insertContractValues = "INSERT INTO contracts (from_date, to_date, odometer, excess_km, " +
                "transfer_km, customer_number, motorhome, delivered, picked_up, closed) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        template.update(insertContractValues, contract.getFromDate(), contract.getToDate(),
                contract.getOdometer(), contract.getExcessKm(), contract.getTransferKm(), contract.getCustomerNumber(),
                contract.getMotorhome(), contract.isDelivered(), contract.isPickedUp(), contract.isClosed());
    }

    /**
     * @param contractId
     * @return
     * @author Sverri
     */
    public Contract findContract(int contractId) {
        String selectSql = "SELECT contract_id AS contractId, from_date AS fromDate, to_date AS toDate," +
                "odometer, customer_number AS customer, customer_number AS customerNumber, motorhome " +
                "FROM contracts WHERE contract_id = ?";
        RowMapper<Contract> rowMapper = new BeanPropertyRowMapper<>(Contract.class);
        return template.queryForObject(selectSql, rowMapper, contractId);
    }

    /**
     * @param contract
     * @author Sverri
     */
    public void editContract(Contract contract) {
        String updateSql = "UPDATE contracts SET from_date = ?, to_date = ?, odometer = ?, customer_number = ?, motorhome = ?, " +
                "excess_km = ?, transfer_km = ?, delivered = ?, picked_up = ?, closed = ?" +
                " WHERE contract_id = ?";
        template.update(updateSql,contract.getFromDate(),contract.getToDate(),contract.getOdometer(), contract.getCustomerNumber(),
                contract.getMotorhome(), contract.getExcessKm(), contract.getTransferKm(), contract.isDelivered(),
                contract.isPickedUp(), contract.isClosed(), contract.getContractId());
    }

    /**
     * @param contractId
     * @author Sverri
     */
    public void deleteContract(int contractId) {
        String deleteSql = "DELETE FROM contracts WHERE contract_id = ?";
        template.update(deleteSql, contractId);
    }

}
