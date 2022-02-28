package cloudmanagementplatform.portaluser.account;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

/**
 * @author agj017@gmail.com
 * @since 2022/02/25
 */


@Controller
public class AccountController {

    @GetMapping("/account")
    public String getAccount(HttpSession session, Model model){



        return "myaccount";
    }


}
