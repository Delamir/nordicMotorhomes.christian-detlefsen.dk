package grp1.motorhomes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Patrick
 */
@Controller
public class HomeController {

    /**
     * @author Patrick
     */
    @GetMapping("/")
    public String index() {
        return "/home/index";
    }
}
