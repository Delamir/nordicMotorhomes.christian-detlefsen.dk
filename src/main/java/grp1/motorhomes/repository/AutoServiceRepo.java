package grp1.motorhomes.repository;

import grp1.motorhomes.model.AutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Christian
 */
@Repository
public class AutoServiceRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * @author Christian
     */
/*
    public List<AutoService> fetchAllAutoServices() {
        String sqlStatement = "SELECT autoservice_id AS autoServiceId, autocheck, motorhome, checked" +
                "FROM autoservices JOIN motorhomes USING (motorhome)";
    }
 */
}
