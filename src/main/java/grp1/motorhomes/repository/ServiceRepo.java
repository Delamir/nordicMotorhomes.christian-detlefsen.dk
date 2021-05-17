package grp1.motorhomes.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author Christian
 */
@Repository
public class ServiceRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;
}
