package cloudmanagementplatform.portaluser.account;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author agj017@gmail.com
 * @since 2022/02/25
 */


@Controller
@RequiredArgsConstructor
public class AccountController {

    final private WebClient oauthWebClient;

    @GetMapping("/account")
    public String getAccount(HttpSession session, Model model){

        ClientResponse response = oauthWebClient.get()
                .uri("/auth/realms/demo/protocol/openid-connect/userinfo")
                .header("Authorization", "Bearer " + session.getAttribute("access_token"))
                .exchange()
                .log()
                .block();

        Map<String, String> body = response.bodyToMono(Map.class).block();

        UserVo userVo = new UserVo();
        userVo.setId(body.get("preferred_username"));
        userVo.setName(body.get("name"));
        userVo.setEmail(body.get("email"));

        model.addAttribute("userVo", userVo);


        return "account";
    }


}
