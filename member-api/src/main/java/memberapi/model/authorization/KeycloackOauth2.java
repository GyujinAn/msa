package memberapi.model.authorization;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;
import java.util.Base64;


@Slf4j
public class KeycloackOauth2 extends CmpOauth2 {

    @Value("${cloudplatform.oauth2.keyclock.host}")
    private String host;

    @Value("${cloudplatform.oauth2.keyclock.realm}")
    private String realm;

    @Value("${cloudplatform.oauth2.keyclock.client_id}")
    private String clientId;

    @Value("${cloudplatform.oauth2.keyclock.client_secret}")
    private String clientSecret;

    @Autowired
    private ApplicationContext appContext;

    public KeycloackOauth2(WebClient oauth2WebClient, RefreshTokenRepo refreshTokenRepo) {
        super(oauth2WebClient, refreshTokenRepo);
    }

    @PostConstruct
    public void init(){
        log.trace("started init() in KeycloackOauth2");

        ClientResponse response = (ClientResponse) oauth2WebClient.post()
                .uri("/auth/realms/" + realm + "/protocol/openid-connect/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData("client_id",clientId)
                        .with("client_secret",clientSecret)
                        .with("grant_type","client_credentials")
                )
                .exchange()
                .log()
                .block();

        if (response.statusCode().value() != 200) {
            log.error("disconnect keycloak server");
            new Exception("can not connect keycloak server; uri -> " + host + ", realm -> " + realm + ", client id -> " +clientId+ ", client secret" + clientSecret).printStackTrace();
            int exitCode = SpringApplication.exit(appContext, () -> 1);
            System.exit(exitCode);
        }

        log.info("succeed to connect keycloak");

    }


    @Override
    ClientResponse getToken(MultiValueMap<String, String> body) {

        body.add("client_id", clientId);
        body.add("client_secret", clientSecret);

        ClientResponse response = oauth2WebClient.post()
                .uri("/auth/realms/" + realm + "/protocol/openid-connect/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(body))
                .exchange()
                .log()
                .block();

        return response;
    }

    @Override
    ClientResponse getUserInfo(String accessToken){

        ClientResponse response = oauth2WebClient.get()
                .uri("/auth/realms/" + realm + "/protocol/openid-connect/userinfo")
                .header("Authorization", "Bearer " + accessToken)
                .exchange()
                .log()
                .block();

        return response;

    }

    @Override
    String getRefreshTokenId(String accessToken) {

        String[] split = accessToken.split("\\.");

        log.debug("accessToken:\n"
                +"header\n"
                + split[0]+"\n"
                +"payload\n"
                +split[1]);


        Base64.Decoder decoder = Base64.getDecoder();
        String payload = new String(decoder.decode(split[1]));

        String sid = null;
        try {
            JSONObject jsonObject= new JSONObject(payload);
            sid = jsonObject.getString("session_state");
            
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sid;
    }

    @Override
    ClientResponse requestLogout(MultiValueMap<String, String> body) {

        body.add("client_id", clientId);
        body.add("client_secret", clientSecret);
        ClientResponse response = oauth2WebClient.post()
                .uri("/auth/realms/" + realm + "/protocol/openid-connect/logout")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(body))
                .exchange()
                .log()
                .block();

        return response;

    }
}
