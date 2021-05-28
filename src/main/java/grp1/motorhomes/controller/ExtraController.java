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

    /**
     * @author Patrick
     * @param model
     */
    @GetMapping("/extraIndex")
    public String extraIndex(Model model) {
        model.addAttribute("extras", extraService.fetchAllExtras());
        return "home/extra/extraIndex";
    }

    /**
     * @author Patrick
     */
    @GetMapping("/createExtra")
    public String createExtra() {
        return "home/extra/createExtra";
    }

    /**
     * @author Patrick
     */
    @PostMapping("/createExtra")
    public String createExtra(@ModelAttribute Extra extra) {
        extraService.createExtras(extra);
        return "redirect:/extraIndex";
    }

    /**
     * @author Patrick
     */
    @PostMapping("/editExtra")
    public String editExtra(@ModelAttribute Extra extra) {
        extraService.editExtra(extra);
        return "redirect:/extraIndex";
    }

    /**
     * @author Patrick
     */
    @GetMapping("/editExtra/{extraId}")
    public String editExtra(@PathVariable("extraId") int extraId, Model model) {
        model.addAttribute("extra", extraService.findExtra(extraId));
        return "home/extra/editExtra";
    }

    /**
     * @author Patrick
     */
    @GetMapping("/deleteExtra/{extraId}")
    public String deleteExtra(@PathVariable("extraId") int extraId) {
        extraService.deleteExtra(extraId);
        return "redirect:/extraIndex";
    }
}
