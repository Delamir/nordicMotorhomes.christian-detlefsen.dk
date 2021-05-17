package grp1.motorhomes.controller;


import grp1.motorhomes.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @auhtor Christian
 */
@Controller
public class ServiceController {

    @Autowired
    ServiceService serviceService;
}
