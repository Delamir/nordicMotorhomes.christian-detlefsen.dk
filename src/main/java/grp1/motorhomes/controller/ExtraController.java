package grp1.motorhomes.controller;

import grp1.motorhomes.model.Extra;
import grp1.motorhomes.service.ExtraService;
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
public class ExtraController {

    @Autowired
    ExtraService extraService;

    @GetMapping("/extraIndex")
    public String extraIndex(Model model) {
        model.addAttribute("extras", extraService.fetchAllExtras());
        return "home/extraIndex";
    }

    @GetMapping("/CreateExtra")
    public String createExtra() {
        return "home/createExtra";
    }

    @PostMapping("/editExtra")
    public String editExtra(@ModelAttribute Extra extra) {
        extraService.editExtra(extra);
        return "redirect:extraIndex";
    }

    @GetMapping("/editExtra/{extraId}")
    public String editExtra(@PathVariable("extraId") int extraId, Model model) {
        model.addAttribute("extra", extraService.findExtra(extraId));
        return "home/editExtra";
    }

    @GetMapping("/deleteExtra/{extraId}")
    public String deleteExtra(@PathVariable("extraId") int extraId) {
        extraService.deleteExtra(extraId);
        return "redirect:/extraIndex";
    }

}
