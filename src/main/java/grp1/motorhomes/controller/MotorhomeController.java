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

@Controller
public class MotorhomeController {

    @Autowired
    MotorhomeService motorhomeService;

    @GetMapping ("/motorhomeIndex")
    public String motorhomeIndex(Model model) {
        List<Motorhome> motorhomeList = motorhomeService.fetchAllMotorhomes();
        model.addAttribute("motorhomes", motorhomeList);
        return "home/motorhomeIndex";
    }

    @GetMapping("/createMotorhome")
    public String createMotorhome() {
        return "home/createMotorhome";
    }

    @PostMapping("/createMotorhome")
    public String createMotorhome(@ModelAttribute Motorhome motorhome) {
        motorhomeService.createMotorhome(motorhome);
        return "redirect:/motorhomeIndex";
    }

}