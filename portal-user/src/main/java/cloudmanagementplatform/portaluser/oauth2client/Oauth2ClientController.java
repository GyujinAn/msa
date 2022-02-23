package cloudmanagementplatform.portaluser.oauth2client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.resource.HttpResource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Base64;
import java.util.Map;

/**
 * @author agj017@gmail.com
 * @since 2022/02/23
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class Oauth2ClientController {

    @Qualifier("oauthWebClient")
    private final WebClient oauthWebClient;

    @GetMapping("/authorize")
    public void authorize(HttpServletRequest httpServletRequest,
                          HttpServletResponse httpServletResponse) throws IOException {

        httpServletResponse.sendRedirect("http://localhost:9001/oauth/authorize?response_type=code&client_id=foo&redirect_uri=http://localhost:8002/callback");

    }

    @GetMapping("/callback")
    public String callback(@RequestParam("code") String code,
                           @Value("${authorization.client-id}")String clientId,
                           @Value("${authorization.client-secret}")String clientSecret,
                           @Value("${authorization.redirect-uri}")String redirectUri,
                           HttpSession session,
                           HttpServletResponse httpServletResponse) throws IOException {

        String credential = clientId + ":" + clientSecret;
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encode = encoder.encode(credential.getBytes());
        String header = new String(encode);

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", "authorization_code");
        formData.add("code", code);
        formData.add("redirect_uri", redirectUri);

        ClientResponse response = oauthWebClient.post()
                .uri("/oauth/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .header("Authorization","Basic "+header)
                .body(BodyInserters.fromFormData(formData))
                .exchange()
                .log()
                .block();

        if(response.statusCode().value() != 200){
            httpServletResponse.sendRedirect("/error.html");
        }else {
            httpServletResponse.sendRedirect("/index.html");
        }

        Map<String, String> res = response.bodyToMono(Map.class).block();

        session.setAttribute("access_token", res.get("access_token"));
        session.setAttribute("token_type", res.get("token_type"));
        session.setAttribute("refresh_token", res.get("refresh_token"));
        session.setAttribute("scope", res.get("scope"));

        String access_token = (String)session.getAttribute("access_token");


        return access_token;

    }

}
