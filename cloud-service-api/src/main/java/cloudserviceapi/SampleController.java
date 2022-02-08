package cloudserviceapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author agj017@gmail.com
 * @since 2022/02/08
 */

@RestController
public class SampleController {

    @GetMapping
    public String getServiceInfo(){

        return "hellow world";
    }
}
