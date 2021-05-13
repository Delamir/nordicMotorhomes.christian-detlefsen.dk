package grp1.motorhomes.repository;

import grp1.motorhomes.model.Contract;
import grp1.motorhomes.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.object.MappingSqlQuery;
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
        String sqlStatement = "SELECT contract_id AS contractId, from_date AS fromDate, to_date AS toDate, " +
                "odometer, price, customer_number AS customer, customer_number AS customerNumber " +
                "FROM contracts ";
        RowMapper<Contract> rowMapper = new BeanPropertyRowMapper<>(Contract.class);
        return template.query(sqlStatement, rowMapper);
    }

    /**
     * @author Christian
     * @param contract
     */
    public void createContract(Contract contract) {
        String insertContractValues = "INSERT INTO contracts (contract_id, from_date, to_date, odometer, price, customer_number)";
        template.update(insertContractValues, contract.getContractId(), contract.getFromDate(), contract.getToDate(),
                contract.getOdometer(), contract.getPrice(), contract.getCustomer());
    }
}
