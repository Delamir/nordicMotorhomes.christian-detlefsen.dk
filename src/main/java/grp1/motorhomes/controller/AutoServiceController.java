package grp1.motorhomes.controller;

import grp1.motorhomes.model.AutoService;
import grp1.motorhomes.service.MotorhomeService;
import grp1.motorhomes.service.AutoServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
    @GetMapping("/autoServiceIndex")
    public String serviceIndex(Model model) {
        List<AutoService> autoServiceList = autoServiceService.fetchAllAutoServices();
        model.addAttribute("services", autoServiceList);
        return "home/autoServiceIndex";
    }

    /**
     * @author Joachim
     */
    @GetMapping ("/createAutoService")
    public String createAutoService(Model model) {
        model.addAttribute("autoSerivce", autoServiceService.fetchAllAutoServices());
        return "home/createAutoService";
    }

    /**
     * @author Joachim
     */
    @PostMapping("/createAutoService")
    public String createAutoService(@ModelAttribute AutoService autoService) {
        autoServiceService.createAutoService(autoService);
        return "redirect:/autoServiceIndex";
    }

    @GetMapping("/editAutoService/{autoServiceId}")
    public String editAutoService(@PathVariable int autoServiceId, Model model) {
        model.addAttribute("autoService", autoServiceService.fetchAllAutoServices());
        return "home/editAutoService";
    }

    @PostMapping("/editAutoService")
    public String editAutoService(@ModelAttribute AutoService autoService) {
        autoServiceService.editAutoservice(autoService);
        return "redirect:/autoServiceIndex";
    }
}