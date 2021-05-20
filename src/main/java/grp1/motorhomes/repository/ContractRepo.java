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
                "FROM contracts LEFT JOIN contracts_extras using(contract_id) LEFT JOIN extras using(extra_id)";
        ContractResultSetExtractor extractor = new ContractResultSetExtractor();
        return (List<Contract>) template.query(sqlStatement, extractor);
    }

    /**
     * @param contract
     * @author Christian
     */
    public void createContract(Contract contract) {
        String insertContractValues = "INSERT INTO contracts (from_date, to_date, odometer, excess_km, " +
                "transfer_km, customer_number, motorhome) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        template.update(insertContractValues, contract.getFromDate(), contract.getToDate(),
                contract.getOdometer(), contract.getExcessKm(), contract.getTransferKm(), contract.getCustomerNumber(),
                contract.getMotorhome());
    }

    /**
     * @param contractId
     * @return
     * @author Sverri
     */
    public Contract findContract(int contractId) {
        String selectSql = "SELECT contract_id, from_date, to_date, " +
                "odometer, excess_km, transfer_km, customer_number, customer_number, " +
                "motorhome, delivery_point, delivered, pickup_point, picked_up, closed, extra_id, price, name, description " +
                "FROM contracts LEFT JOIN contracts_extras using(contract_id) LEFT JOIN extras using(extra_id) WHERE contract_id = ?";
        ContractResultSetExtractor extractor = new ContractResultSetExtractor();
        //RowMapper<Contract> rowMapper = new BeanPropertyRowMapper<>(Contract.class);
        List<Contract> contracts = (List<Contract>) template.query(selectSql, extractor, contractId);
        return contracts.get(0);

    }

    /**
     * @param contract
     * @author Sverri
     */
    public void editContract(Contract contract) {
        String updateSql = "UPDATE contracts SET from_date = ?, to_date = ?, odometer = ?, customer_number = ?, motorhome = ?, " +
                "excess_km = ?, transfer_km = ?, delivery_point = ?, delivered = ?, pickup_point = ?, picked_up = ?, closed = ?" +
                " WHERE contract_id = ?";
        template.update(updateSql,contract.getFromDate(),contract.getToDate(),contract.getOdometer(), contract.getCustomerNumber(),
                contract.getMotorhome(), contract.getExcessKm(), contract.getTransferKm(), contract.getDeliveryPoint(),
                contract.isDelivered(), contract.getPickupPoint(), contract.isPickedUp(), contract.isClosed(), contract.getContractId());
    }

    public void editExtra(Extra extra){
        String updateSql = "UPDATE extras SET price = ?, name = ?, description = ? WHERE extra_id = ?";
            template.update(updateSql, extra.getPrice(), extra.getName(), extra.getDescription(), extra.getExtraId());
    }

    /**
     * @param contractId
     * @author Sverri
     */
    public void deleteContract(int contractId) {
        String deleteSql = "DELETE FROM contracts_extras WHERE contract_id = ?";
        template.update(deleteSql, contractId);
        deleteSql = "DELETE FROM contracts WHERE contract_id = ?";
        template.update(deleteSql, contractId);
    }

    public void deliverContract(Contract contract) {
        String updateSql = "UPDATE contracts SET delivered = true, transfer_km = ? WHERE contract_id = ?";
        template.update(updateSql, contract.getTransferKm(), contract.getContractId());
    }

    /**
     * @author Joachim
     * @param contract
     */
    public void pickupContract(Contract contract) {
        String updateSql = "UPDATE contracts SET picked_up = true, pickup_point = ? WHERE contract_id = ?";
        template.update(updateSql, contract.getPickupPoint(), contract.getContractId());
    }
}
