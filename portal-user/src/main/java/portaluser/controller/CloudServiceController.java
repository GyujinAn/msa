package portaluser.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CloudServiceController {

    @GetMapping("/services")
    public String getServices(){
        return "service";
    }

}
