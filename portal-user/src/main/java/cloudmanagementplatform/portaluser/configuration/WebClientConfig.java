package cloudmanagementplatform.portaluser.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author agj017@gmail.com
 * @since 2022/02/23
 */

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient oauthWebClient(@Value("${authorization.base-uri}")String baseUrl){

        return WebClient.builder()
                .baseUrl(baseUrl)
                .build();

    }

    @Bean
    public WebClient serviceApiWebClient(@Value("${api.service.base-uri}")String baseUrl){

        return WebClient.builder()
                .baseUrl(baseUrl)
                .build();

    }

}
