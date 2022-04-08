package cmpapigateway.filter;

import cmpapigateway.data.KeycloakUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class AuthorizationGatewayFilterFactory extends AbstractGatewayFilterFactory<AuthorizationGatewayFilterFactory.Config> {

    private final WebClient keyCloakWebClient;

    @Autowired
    public AuthorizationGatewayFilterFactory(WebClient keyCloakWebClient) {
        super(Config.class);
        this.keyCloakWebClient = keyCloakWebClient;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            log.debug("Pre Authorization filter is started");

            HttpHeaders headers = exchange.getRequest().getHeaders();
            String accessToken = headers.getFirst("Authorization");
            if(accessToken != null){
                String[] arr = accessToken.split(" ");
                accessToken = arr[1];
            }
            log.debug("여기---------------------1");
            String finalAccessToken = accessToken;
            Mono<KeycloakUserInfo> mono =  keyCloakWebClient.get()
                    .uri("/auth/realms/auth/realms/master/protocol/openid-connect/userinfo/")
                    .headers(h -> {
                        h.add("Authorization", "Bearer " + finalAccessToken);
                    })
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .onStatus(HttpStatus::is1xxInformational,clientResponse -> {
                        log.debug("안규진1");
                        return null;
                    })
                    .onStatus(HttpStatus::is2xxSuccessful, clientResponse -> {
                        log.debug("안규진2");
                        return null;
                    })
                    .onStatus(HttpStatus::is3xxRedirection, clientResponse -> {
                        log.debug("안규진3");
                        return null;
                    })
                    .onStatus(HttpStatus::is4xxClientError, clientResponse -> {
                        log.debug("안규진4");
                        return null;
                    })
                    .onStatus(HttpStatus::is5xxServerError, clientResponse -> {
                        log.debug("안규진5");
                        return null;
                    })
                    .bodyToMono(KeycloakUserInfo.class);
//                    .exchangeToMono(clientResponse -> {
//                        log.debug("안규진:1");
//                        if(clientResponse.statusCode().equals(HttpStatus.OK)){
//                            log.debug("안규진:2");
//                            return clientResponse.bodyToMono(KeycloakUserInfo.class);
//                        }else {
//                            log.debug("안규진:3");
//                            return clientResponse.createException().flatMap(Mono::error);
//                        }
//
//                    });
            log.debug("여기---------------------2");


            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                log.debug("Post Authorization filter is started");

            }));
        });
    }

    public static class Config{

    }
}
