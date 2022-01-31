package cloudgateway.filter;

import cloudgateway.data.HttpMessageEntity;
import cloudgateway.data.HttpMessageRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class FirstGlobalFilter implements GlobalFilter, Ordered {


    private final HttpMessageRepo httpMessageRepo;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.debug("Pre first global filter is started");
        ServerHttpRequest request = exchange.getRequest();
        HttpHeaders headers = request.getHeaders();
        Long start = System.currentTimeMillis();
        HttpMessageEntity httpMessageEntity = new HttpMessageEntity();

        httpMessageEntity.setMethod(request.getMethod().toString());
        httpMessageEntity.setPath(request.getPath().toString());
        httpMessageEntity.setRemoteIpAddr(request.getRemoteAddress().toString());
        httpMessageEntity.setHost(headers.getHost().toString());
        httpMessageEntity.setUserIdFromAuthorization(headers.getFirst("Authorization"));
        httpMessageEntity.setDivisionFromPath(request.getPath().toString());


        return chain.filter(exchange)
                .then(Mono.fromRunnable(() -> {
                    log.debug("Post first global filter is started");
                    ServerHttpResponse response = exchange.getResponse();
                    Long end = System.currentTimeMillis();



                    httpMessageEntity.setStatus(response.getStatusCode().toString());
                    httpMessageEntity.setCreatedAt(LocalDateTime.now());
                    httpMessageEntity.setPerformance(end - start);
                    httpMessageRepo.save(httpMessageEntity);

                }));
    }



    @Override
    public int getOrder() {
        return -1;
    }
}


