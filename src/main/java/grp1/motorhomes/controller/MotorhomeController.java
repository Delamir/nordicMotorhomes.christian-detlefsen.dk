package grp1.motorhomes.controller;

import grp1.motorhomes.model.Motorhome;
import grp1.motorhomes.service.MotorhomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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
        List<Motorhome> motorhomeList = motorhomeService.fetchAllMotorhomes();
        model.addAttribute("motorhomes", motorhomeList);
        System.out.println(motorhomeList);
        return "home/motorhomeIndex";
    }

    /**
     * @author Patrick
     */
    @GetMapping("/createMotorhome")
    public String createMotorhome() {
        return "home/createMotorhome";
    }

    /**
     * @author Patrick
     */
    @PostMapping("/createMotorhome")
    public String createMotorhome(@ModelAttribute Motorhome motorhome) {
        motorhomeService.createMotorhome(motorhome);
        return "redirect:/motorhomeIndex";
    }

}