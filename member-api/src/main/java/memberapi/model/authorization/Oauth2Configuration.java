package memberapi.model.authorization;

import io.netty.channel.ChannelOption;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import javax.net.ssl.SSLException;
import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
public class Oauth2Configuration {

    @Value("${cloudplatform.oauth2.keyclock.host}")
    private String host;

    @Autowired
    private RefreshTokenRepo refreshTokenRepo;

    @Autowired
    @Qualifier("keyCloakWebClient")
    private WebClient webClient;

    @Bean
    public CmpOauth2 keycloackQauth2(){
        log.debug("started keycloackQauth2()");
//        WebClient webClient;
//        try {
//            webClient = oauth2WebClient();
//        } catch (SSLException e) {
//            log.error("failed to create webclient");
//            e.printStackTrace();
//        }

        return new KeycloackOauth2(webClient, refreshTokenRepo);
    }

    @Bean
    public WebClient oauth2WebClient() throws SSLException {
        SslContext sslContext = SslContextBuilder
                .forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                .build();

        HttpClient httpClient = HttpClient.create()
                .tcpConfiguration(client ->
                        client.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 7000)
                                .doOnConnected(conn -> conn
                                        .addHandlerLast(new ReadTimeoutHandler(7000, TimeUnit.MILLISECONDS))
                                        .addHandlerLast(new WriteTimeoutHandler(7000, TimeUnit.MILLISECONDS))))
                .secure(spec ->
                        spec.sslContext(sslContext));

        ClientHttpConnector httpConnector = new ReactorClientHttpConnector(httpClient);

        return WebClient
                .builder()
                .baseUrl(host)
                .clientConnector(httpConnector)
                .build();
    }

}
