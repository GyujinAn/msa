package cmpapigateway.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * @author agj017@gmail.com
 * @since 2022/03/08
 */
@Slf4j
@Component
public class JwtValidationFilterFactory extends AbstractGatewayFilterFactory<JwtValidationFilterFactory.Config> {
    public JwtValidationFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            log.debug("Pre Authorization filter is started");
            ServerHttpRequest request = exchange.getRequest();
            if(!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                return completeResponse(exchange, "Not found authorization header", HttpStatus.UNAUTHORIZED);
            }
            HttpHeaders headers = exchange.getRequest().getHeaders();
            String accessToken = headers.getFirst("Authorization");
            String[] arr = accessToken.split(" ");
            accessToken = arr[1];

            if(!isJwtValid(accessToken, config.getPublicKey())){
                return completeResponse(exchange, "JWT token is not valid", HttpStatus.UNAUTHORIZED);
            }


            if(exchange.getRequest().getPath().toString().equals("/admin/api/auth")){
                return completeResponse(exchange, "Success login", HttpStatus.OK);
            }


            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                log.debug("Post Authorization filter is started");

            }));
        });
    }

    private Mono<Void> completeResponse(ServerWebExchange exchange, String e, HttpStatus status){
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(status);
        log.debug(e);
        return response.setComplete();
    }

    private boolean isJwtValid(String token, String rsaPublicKey){

        try {

            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(rsaPublicKey));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(keySpec);

            Jws<Claims> jwt = null;
            jwt = Jwts.parserBuilder()
                    .setSigningKey(publicKey)
                    .build()
                    .parseClaimsJws(token);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return false;
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;

    }

    @Data
    public static class Config{
        private String publicKey;
        private boolean preFilter;
        private boolean postFilter;

    }
}
