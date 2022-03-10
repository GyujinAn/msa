package cloudmanagementplatform.portaluser.service;

import cloudmanagementplatform.portaluser.account.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author agj017@gmail.com
 * @since 2022/03/10
 */
@Controller
@RequiredArgsConstructor
public class ServiceController {

    final private WebClient serviceApiWebClient;

    @GetMapping("/services")
    public String getAccount(HttpSession session, Model model){

        ClientResponse response = serviceApiWebClient.get()
                .uri("/api/cloudServices")
                .header("Authorization", "Bearer " + session.getAttribute("access_token"))
                .exchange()
                .log()
                .block();

        Map<String, String> body = response.bodyToMono(Map.class).block();

        List list = Arrays.asList(body.get("_embedded"));
        model.addAttribute("services", list);


        return "service";
    }

}
