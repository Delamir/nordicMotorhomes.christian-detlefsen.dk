package grp1.motorhomes.service;

import grp1.motorhomes.repository.ServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Christian
 */
@Service
public class ServiceService {

    @Autowired
    ServiceRepo serviceRepo;
}
