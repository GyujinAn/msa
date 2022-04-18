package cmpmemberapi.service;

import cmpmemberapi.configuration.OIDCProperties;
import cmpmemberapi.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * @author agj017@gmail.com
 * @since 2022/04/15
 */
@Slf4j
@RequiredArgsConstructor
public class KeycloakDecorator implements MemberService{

    private final MemberService memberService;

    private final WebClient keycloakWebClient;

    private final OIDCProperties oidcProperties;

    @Autowired
    private ApplicationContext appContext;

    private String accessToken;

    @PostConstruct
    private void init(){
        try {
            setAccessToken();
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();

            int exitCode = SpringApplication.exit(appContext, () -> 1);
            System.exit(exitCode);
        }

    }

    private void setAccessToken() throws Exception{

        ClientResponse clientResponse = keycloakWebClient.post()
                .uri("/auth/realms/master/protocol/openid-connect/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.
                        fromFormData("username", oidcProperties.getUsername())
                        .with("password", oidcProperties.getPassword())
                        .with("client_id", oidcProperties.getClientId())
                        .with("grant_type", oidcProperties.getGrantType()))
                .exchange()
                .log()
                .block();



        if(clientResponse.statusCode().value() != 200){
            throw new Exception("failed to get access token; check your oidc properties: " + oidcProperties.toString());
        }else {
            Mono<Map> mapMono = clientResponse.bodyToMono(Map.class);
            mapMono.subscribe(body -> {
                accessToken = (String)body.get("access_token");
            });
        }

    }

    @Override
    public Long saveMember(MemberDto memberDto) {

//        String json = "{\"hello\" : \"world\"," +
//                "}";
//
//        keycloakWebClient.post()
//                .uri("/auth/admin/realms/cmp/users")
//                .contentType(MediaType.APPLICATION_JSON)
//                .headers(headers -> {
//                    headers.add("Authorization", "Bearer " + accessToken);
//                }).retrieve();

        return memberService.saveMember(memberDto);
    }

    @Override
    public Long updateMember(String memberId, MemberDto memberDto) {

        return memberService.updateMember(memberId, memberDto);
    }

    @Override
    public MemberDto findMember(String memberId) {

        return memberService.findMember(memberId);
    }

    @Override
    public MemberDto findMembers() {

        return memberService.findMembers();
    }

    @Override
    public boolean deleteMember() {

        return memberService.deleteMember();
    }
}
