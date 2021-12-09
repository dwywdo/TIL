package tacos;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        /**
         * Returns the view name. It can be used as a logical name for view
         */
        return "home";
    }
}
