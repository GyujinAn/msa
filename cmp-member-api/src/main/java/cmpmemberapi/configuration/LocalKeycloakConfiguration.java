package cmpmemberapi.configuration;

import cmpmemberapi.domain.Member;
import cmpmemberapi.repository.MemberRepository;
import cmpmemberapi.service.KeycloakDecorator;
import cmpmemberapi.service.MemberService;
import cmpmemberapi.service.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author agj017@gmail.com
 * @since 2022/04/15
 */

@Profile("localwithkeycloak")
@Configuration
public class LocalKeycloakConfiguration {

    @Autowired
    OIDCProperties oidcProperties;

    @Autowired
    MemberService memberService;

    @Bean
    MemberService memberServiceWithKeycloak(){
        return new KeycloakDecorator(memberService, keycloakWebClient(), oidcProperties);
    }

    @Bean
    WebClient keycloakWebClient(){
        return WebClient
                .builder()
                .baseUrl(oidcProperties.getProtocol() + oidcProperties.getUrl() )
                .build();
    }

}
