package cmpapigateway.networkpolicy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class HttpPolicyGatewayFilterFactory extends AbstractGatewayFilterFactory<HttpPolicyGatewayFilterFactory.Config> {


    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {


            return chain.filter(exchange).then(Mono.fromRunnable(() -> {


            }));
        });
    }

    private Mono<Void> completeResponse(ServerWebExchange exchange, String e, HttpStatus status){
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(status);
        log.debug(e);
        return response.setComplete();
    }


    public static class Config {

    }
}
