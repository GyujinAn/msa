package memberapi.authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import javax.net.ssl.SSLException;

@Slf4j
@Configuration
public class Oauth2Configuration {

    @Value("${keycloak.enabled}")
    private boolean keycloackEnabled;

    @Value("${keycloak.host}")
    private String keycloackHost;

    @Value("${keycloak.realm}")
    private String keycloackRealm;

    @Value("${keycloak.cliend_id}")
    private String keycloackCliendId;

    @Value("${keycloak.client_secret}")
    private String keycloackClientSecret;

    @Autowired
    private RefreshTokenRepo refreshTokenRepo;

    @Bean
    public CmpOauth2 keycloackQauth2() throws SSLException {
        log.debug("started keycloackQauth2()");

        if(!keycloackEnabled){
            return null;
        }

        return new KeycloackOauth2(keycloakWebClient(), refreshTokenRepo);
    }

    @Bean
    public WebClient keycloakWebClient() throws SSLException {

        return WebClient
                .builder()
                .baseUrl(keycloackHost)
                .build();
    }

}
