package cmpapigateway.authorization;


import cmpapigateway.util.JwtTokenHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class AuthorizationGatewayFilterFactory extends AbstractGatewayFilterFactory<AuthorizationGatewayFilterFactory.Config> {

    @Value("${oauth.public-key}")
    private String rsaPublicKey;

    final private JwtTokenHandler jwtTokenHandler;



    @Autowired
    public AuthorizationGatewayFilterFactory(JwtTokenHandler jwtTokenHandler) {

        super(Config.class);
        this.jwtTokenHandler = jwtTokenHandler;

    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            log.debug("Pre AuthorizationGatewayFilter is started");
            ServerHttpRequest request = exchange.getRequest();
            if(!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                return completeResponse(exchange, "Not found authorization header", HttpStatus.UNAUTHORIZED);
            }

            String accessTokenFromHeader = jwtTokenHandler.getAccessTokenFromHeader(exchange.getRequest().getHeaders());

            if(!jwtTokenHandler.isJwtValid(accessTokenFromHeader)){

                return completeResponse(exchange, "JWT token is not valid", HttpStatus.UNAUTHORIZED);
            }


            if(exchange.getRequest().getPath().toString().equals("/admin/api/auth")
            || exchange.getRequest().getPath().toString().equals("/user/api/auth")){
                return completeResponse(exchange, "Success login", HttpStatus.OK);
            }


            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                log.debug("Post AuthorizationGatewayFilter is started");

            }));
        });
    }


    private Mono<Void> completeResponse(ServerWebExchange exchange, String e, HttpStatus status){
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(status);
        log.debug(e);
        return response.setComplete();
    }


    public static class Config{

    }
}
