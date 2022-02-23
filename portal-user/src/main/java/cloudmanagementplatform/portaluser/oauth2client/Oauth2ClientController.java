package cloudmanagementplatform.portaluser.oauth2client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

/**
 * @author agj017@gmail.com
 * @since 2022/02/23
 */
@RestController
@RequiredArgsConstructor
public class Oauth2ClientController {

    @Qualifier("oauthWebClient")
    private final WebClient oauthWebClient;

    @GetMapping("/authorize")
    public void authorize(){

    }

    @GetMapping("/callback")
    public String callback(@RequestParam("code") String code,
                         @Value("${authorization.client-id}")String clientId,
                         @Value("${authorization.client-secret}")String clientSecret,
                         @Value("${authorization.redirect-uri}")String redirectUri){

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("code", code);
        formData.add("redirect_uri", redirectUri);
        formData.add("clientId", clientId);
        formData.add("client_secret", clientSecret);



        ClientResponse response = oauthWebClient.post()
                .uri("/oauth/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(formData))
                .exchange()
                .log()
                .block();

        Map res = response.bodyToMono(Map.class).block();


        return res.toString();

    }

}
