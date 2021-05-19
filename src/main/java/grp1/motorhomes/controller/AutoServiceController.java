package grp1.motorhomes.controller;

import grp1.motorhomes.model.AutoService;
import grp1.motorhomes.service.MotorhomeService;
import grp1.motorhomes.service.AutoServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @auhtor Christian
 */
@Controller
public class AutoServiceController {

    @Autowired
    AutoServiceService autoServiceService;

    @Autowired
    MotorhomeService motorhomeService;

    /**
     * @Author Christian
     */
    /*
    @GetMapping("/serviceIndex")
    public String serviceIndex(Model model) {
        List<AutoService> autoServiceList = autoServiceService.fetchAllAutoServices();
        model.addAttribute("services", autoServiceList);
        return "home/serviceIndex";
    }

     */
}