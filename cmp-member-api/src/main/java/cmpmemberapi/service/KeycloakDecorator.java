package cmpmemberapi.service;

import cmpmemberapi.configuration.OIDCProperties;
import cmpmemberapi.domain.Roles;
import cmpmemberapi.dto.MemberDto;
import cmpmemberapi.repository.MemberRoleRepository;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author agj017@gmail.com
 * @since 2022/04/15
 */
@Slf4j
@RequiredArgsConstructor
public class KeycloakDecorator implements MemberService{

    private final MemberService memberService;

    private final WebClient oicdWebClient;

    private final OIDCProperties oidcProperties;

    private final MemberRoleRepository memberRoleRepository;

    @Autowired
    private ApplicationContext appContext;

    private String accessToken;

    @PostConstruct
    private void init(){

        try {
            updateAccessToken();
            saveRealmRoleFromOicdServer();
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();

            int exitCode = SpringApplication.exit(appContext, () -> 1);
            System.exit(exitCode);
        }

    }

    private void updateAccessToken() throws Exception{

        System.out.println("here is update access token");
        System.out.println(oidcProperties.toString());

        ClientResponse clientResponse = oicdWebClient.post()
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
            Map body = clientResponse.bodyToMono(Map.class).block();
            accessToken = (String)body.get("access_token");
            System.out.println("print access token " + accessToken);
        }

    }

    private void saveRealmRoleFromOicdServer() throws Exception {

        ClientResponse clientResponse = oicdWebClient.get()
                .uri("/auth/admin/realms/cmp/roles")
                .header("Authorization", "Bearer " + accessToken)
                .exchange()
                .log()
                .block();

        if(clientResponse.statusCode().value() == 401){
            updateAccessToken();
            saveRealmRoleFromOicdServer();
        }

        Mono<List> listMono = clientResponse.bodyToMono(List.class);

        listMono.subscribe(body -> {
            body.stream().forEach(map -> {

                Map<String, String> tmp = (Map)map;

                Roles roles = Roles.builder()
                        .id(tmp.get("id"))
                        .name(tmp.get("name"))
                        .build();
                memberRoleRepository.save(roles);
            });
        });
    }

    private void createUserInOidcServer(MemberDto memberDto) throws Exception {
        updateAccessToken();

        Map<String, Object> body = new HashMap<>();
        body.put("username",memberDto.getAccount());
        body.put("enabled", "true");
        body.put("firstName",memberDto.getName());

        Map<String, String> credential = new HashMap<>();
        credential.put("type", "password");
        credential.put("value", memberDto.getPassword());
        credential.put("temporary", "false");

        body.put("credentials", new ArrayList<Map>().add(credential));

        ClientResponse clientResponse = oicdWebClient.post()
                .uri("/auth/admin/realms/okestro_cmp/users")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + accessToken)
                .body(BodyInserters.fromValue(body))
                .exchange()
                .log()
                .block();

        if(clientResponse.statusCode().value() != 200){
            throw new Exception("failed create user in oidc server");
        }
    }

    private String getUserId(String username) throws Exception {
        updateAccessToken();

        ClientResponse clientResponse = oicdWebClient.get()
                .uri("/auth/admin/realms/okestro_cmp/users?username=" + username)
                .header("Authorization", "Bearer " + accessToken)
                .exchange()
                .log()
                .block();

        if(clientResponse.statusCode().value() != 200){
            throw new Exception("failed get user in oidc server");
        }

        Map body = clientResponse.bodyToMono(Map.class).block();

        return (String)body.get("id");

    }

    private String addRealmRoleMappingsToUser(){



        return "";

    }



    @Override
    public Long saveMember(MemberDto memberDto) throws Exception {
        updateAccessToken();

        createUserInOidcServer(memberDto);

        String userId = getUserId(memberDto.getAccount());




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
