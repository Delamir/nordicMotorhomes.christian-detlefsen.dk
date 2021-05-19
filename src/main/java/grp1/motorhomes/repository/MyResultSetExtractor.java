package grp1.motorhomes.repository;

import grp1.motorhomes.model.Contract;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * @author Sverri
 * customer ResultSetExtractor so that we can map multiple rows in a join query to a single object
 */
public class MyResultSetExtractor implements ResultSetExtractor {

    /**
     * the method that is run when the class tries to map data from a resultSet from the JdbcTemplate
     * @param resultSet
     * @return
     * @throws SQLException
     * @throws DataAccessException
     */
    @Override
    public Object extractData(ResultSet resultSet) throws SQLException, DataAccessException {

        // we use hash map to make sure there is only one object of a contract with a given contract id
        HashMap<Integer, Contract> contracts = new HashMap<>();

        // we run trough all the rows of the query
        while(resultSet.next()){

            // we see if the contract is already in the hash map
            Contract contract = contracts.get(resultSet.getInt("contract_id"));

            // if it is not we make a new contract object and put it in the map
            if(contract == null){

                contract = new Contract();
                contract.setContractId(resultSet.getInt("contract_id"));
                contract.setFromDate(resultSet.getTimestamp("fromDate").toString());
                contract.setToDate(resultSet.getTimestamp("toDate").toString());
                contract.setOdometer(resultSet.getInt("odometer"));
                contract.setCustomerNumber(resultSet.getInt("customer_number"));
                contract.setMotorhome(resultSet.getString("motorhome"));
                contract.setExcessKm(resultSet.getInt("excess_km"));
                contract.setTransferKm(resultSet.getInt("transfer_km"));

                contracts.put(contract.getContractId(),contract);

            }

            // lastly we add the extra of the current row of the result set
            contract.addExtra(resultSet.getInt("extra_id"), resultSet.getInt("price"),
                    resultSet.getString("name"), resultSet.getString("description"));

        }

        return contracts;
    }
}
