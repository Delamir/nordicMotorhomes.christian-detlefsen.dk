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
    public String autoServiceIndex(Model model) {
        model.addAttribute("autoServices", autoServiceService.fetchAllAutoServices());
        model.addAttribute("motorhomes", motorhomeService.fetchAllUnavailableMotorhomes());
        return "home/autoservice/autoServiceIndex";
    }

    /**
     * @author Joachim
     */
    @GetMapping ("/createAutoService/{licencePlate}")
    public String createAutoService(@PathVariable String licencePlate, Model model) {
        model.addAttribute("licencePlate", licencePlate);
        return "home/autoservice/createAutoService";
    }

    /**
     * @author Joachim
     */
    @PostMapping("/createAutoService")
    public String createAutoService(@ModelAttribute AutoService autoService) {
        autoServiceService.createAutoService(autoService);
        return "redirect:/autoServiceIndex";
    }

    /**
     * @author Joachim
     */
    @GetMapping("/editAutoService/{autoServiceId}")
    public String editAutoService(@PathVariable int autoServiceId, Model model) {
        model.addAttribute("autoService", autoServiceService.findAutoService(autoServiceId));
        return "home/autoservice/editAutoService";
    }

    /**
     * @author Joachim
     */
    @PostMapping("/editAutoService")
    public String editAutoService(@ModelAttribute AutoService autoService) {
        autoServiceService.editAutoService(autoService);
        return "redirect:/autoServiceIndex";
    }

    /**
     * @author Christian
     */
    @PostMapping("/markDone")
    public String markDone(@ModelAttribute AutoService autoService) {
        autoServiceService.markDone(autoService);
        motorhomeService.setAvailable(autoService.getMotorhome().getLicencePlate(), true);
        return "redirect:/autoServiceIndex";
    }

    /**
     * @author Christian
     */
    @GetMapping("/makeAvailable/{licencePlate}")
    public String makeAvailable(@PathVariable String licencePlate) {
        motorhomeService.setAvailable(licencePlate, true);
        return "redirect:/autoServiceIndex";
    }
}