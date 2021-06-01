package grp1.motorhomes.controller;

import grp1.motorhomes.model.Motorhome;
import grp1.motorhomes.service.MotorhomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Patrick
 */
@Controller
public class MotorhomeController {

    @Autowired
    MotorhomeService motorhomeService;

    /**
     * @author Patrick
     */
    @GetMapping ("/motorhomeIndex")
    public String motorhomeIndex(Model model) {
        model.addAttribute("motorhomes", motorhomeService.fetchAllMotorhomes());
        return "home/motorhome/motorhomeIndex";
    }

    /**
     * @author Patrick
     */
    @GetMapping("/createMotorhome")
    public String createMotorhome() {
        return "home/motorhome/createMotorhome";
    }

    /**
     * @author Patrick
     * If we create a motorhome with the same licenceplate, the method throws an exception telling the user
     * that a licenceplate with the same number already exists
     */
    @PostMapping("/createMotorhome")
    public String createMotorhome(@ModelAttribute Motorhome motorhome, Model model) {

        try {
            motorhomeService.createMotorhome(motorhome);
            return "redirect:/motorhomeIndex";
        } catch (Exception e) {
            model.addAttribute("duplicateError", "Duplicate, Licence plate already in system");
            return "home/motorhome/createMotorhome";
        }
    }

    /**
     * @author Patrick
     */
    @PostMapping("/editMotorhome")
    public String editMotorhome(@ModelAttribute Motorhome motorhome) {
        motorhomeService.editMotorhome(motorhome);
        return "redirect:/motorhomeIndex";
    }

    /**
     * @author Patrick
     */
    @GetMapping("/editMotorhome/{licencePlate}")
    public String editMotorhome(@PathVariable("licencePlate") String licencePlate, Model model) {
        model.addAttribute("motorhome", motorhomeService.findMotorhome(licencePlate));
        return "home/motorhome/editMotorhome";
    }

    /**
     * @author Patrick
     */
    @GetMapping("/deleteMotorhome/{licencePlate}")
    public String deleteMotorhome(@PathVariable("licencePlate") String licencePlate) {
        motorhomeService.deleteMotorhome(licencePlate);
        return "redirect:/motorhomeIndex";
    }
}