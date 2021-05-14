package grp1.motorhomes.repository;

import grp1.motorhomes.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Joachim
 */
@Repository
public class CustomerRepo {

    @Autowired
    JdbcTemplate template;

    /**
     * @author Joachim
     */
    public List<Customer> fetchAllCustomers() {
        String sql = "SELECT customer_number AS customerNumber, name, licence_number AS licenceNumber, post_code AS postCode, street, city " +
                "FROM customers JOIN addresses USING(customer_number)";
        RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class);
        return template.query(sql, rowMapper);
    }
/*
    public void createCustomer(Customer customer) {

        String insertModel ="INSERT INTO ";
        template.update(insertModel, );
        }
 */
    }