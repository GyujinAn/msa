package cmpapigateway.apilog;


import cmpapigateway.util.JwtTokenHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Slf4j
@Component
public class HttpLogGatewayFilterFactory extends AbstractGatewayFilterFactory<HttpLogGatewayFilterFactory.Config> {

    final private JwtTokenHandler jwtTokenHandler;

    final private ApiLogRepo apiLogRepo;

    @Autowired
    public HttpLogGatewayFilterFactory(JwtTokenHandler jwtTokenHandler, ApiLogRepo apiLogRepo) {
        super(Config.class);
        this.jwtTokenHandler = jwtTokenHandler;
        this.apiLogRepo = apiLogRepo;
    }

    @Override
    public GatewayFilter apply(Config config) {

        return ((exchange, chain) -> {
            log.debug("Pre HttpMsgLogGatewayFilter is started");
            ServerHttpRequest request = exchange.getRequest();
            HttpHeaders headers = request.getHeaders();
            Long start = System.currentTimeMillis();

            ApiLog apiLog = new ApiLog();
            apiLog.setMethod(request.getMethod().toString());
            apiLog.setPath(request.getPath().toString());
            apiLog.setRemoteIpAddr(request.getRemoteAddress().toString());
            apiLog.setHost(headers.getHost().toString());
            apiLog.setUserId(jwtTokenHandler.getUsername(headers));
//            httpMessageEntity.setDivisionFromPath(request.getPath().toString());

            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                log.debug("Post HttpMsgLogGatewayFilter is started");
                ServerHttpResponse response = exchange.getResponse();
                Long end = System.currentTimeMillis();

                apiLog.setStatus(response.getStatusCode().toString());
                apiLog.setCreatedAt(LocalDateTime.now());
                apiLog.setPerformance(end - start);
                apiLogRepo.save(apiLog);
            }));
        });
    }

    public static class Config {

    }
}
