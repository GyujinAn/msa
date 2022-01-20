package memberapi.authorization;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;


@Slf4j
@RequiredArgsConstructor
public abstract class CmpOauth2 {

    final protected WebClient oauth2WebClient;

    final private RefreshTokenRepo refreshTokenRepo;

    public CmpOauth2Response login(String id, String pw){
        CmpOauth2Response cmpOauth2Response = new CmpOauth2Response();

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("username", id);
        requestBody.add("password", pw);
        requestBody.add("grant_type", "password");
        ClientResponse response = getToken(requestBody);
//        log.debug("상태코드: " + response.statusCode().value());
        if(response.statusCode().value() != 200){
            cmpOauth2Response.setState(CmpOauth2Response.State.Unauthorized);
            return  cmpOauth2Response;
        }

        Map responseBody = response.bodyToMono(Map.class).block();
        saveRefreshToken(responseBody);
        cmpOauth2Response.setState(CmpOauth2Response.State.NoContent);
        cmpOauth2Response.setAccessToken((String)responseBody.get("access_token"));

        return cmpOauth2Response;
    }


    public CmpOauth2Response validateUser(String accessToken){
        CmpOauth2Response cmpOauth2Response = new CmpOauth2Response();
        RefreshTokenEntity refreshTokenEntity = null;
        ClientResponse response = getUserInfo(accessToken);

        if(response.statusCode().value() == 200){
            cmpOauth2Response.setState(CmpOauth2Response.State.OK);
            return cmpOauth2Response;
        }

        String refreshTokenId = getRefreshTokenId(accessToken);
        try{
            Optional<RefreshTokenEntity> refreshTokenEntityOptional = refreshTokenRepo.findById(refreshTokenId);
            refreshTokenEntity = refreshTokenEntityOptional.get();
        }catch (Exception e){
            cmpOauth2Response.setState(CmpOauth2Response.State.Unauthorized);
            return cmpOauth2Response;
        }


        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("refresh_token", refreshTokenEntity.getRefreshToken());
        requestBody.add("grant_type", "refresh_token");
        response = getToken(requestBody);

        if(response.statusCode().value() != 200){
            refreshTokenRepo.delete(refreshTokenEntity);
            cmpOauth2Response.setState(CmpOauth2Response.State.Unauthorized);
            return cmpOauth2Response;
        }

        Map responseBody = response.bodyToMono(Map.class).block();
        updateRefresToken(responseBody);
        cmpOauth2Response.setState(CmpOauth2Response.State.NoContent);
        cmpOauth2Response.setAccessToken((String)responseBody.get("access_token"));

        return cmpOauth2Response;
    }


    public CmpOauth2Response logout(String accessToken){
        CmpOauth2Response cmpOauth2Response = new CmpOauth2Response();
        String refreshTokenId = getRefreshTokenId(accessToken);
        Optional<RefreshTokenEntity> refreshTokenEntityOptional = refreshTokenRepo.findById(refreshTokenId);
        RefreshTokenEntity refreshTokenEntity;

        try{
            refreshTokenEntity = refreshTokenEntityOptional.get();

        }catch (NoSuchElementException exception){
            cmpOauth2Response.setState(CmpOauth2Response.State.BadRequest);
            log.error("can not find by " + refreshTokenId +" in KEYCLOCK_REFRESH_TOKEN Table");
            exception.printStackTrace();
            return cmpOauth2Response;
        }

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("refresh_token", refreshTokenEntity.getRefreshToken());
        ClientResponse response = requestLogout(requestBody);

        Map responseBody = response.bodyToMono(Map.class).block();

        if(response.statusCode().value() != 204){
            log.error(responseBody.get("error") + ":  " +   responseBody.get("error_description"));
            cmpOauth2Response.setState(CmpOauth2Response.State.BadRequest);
            return cmpOauth2Response;
        }
        cmpOauth2Response.setState(CmpOauth2Response.State.NoContent);

        return cmpOauth2Response;

    }

    private void saveRefreshToken(Map<String, String> body){

        RefreshTokenEntity refreshTokenEntity = new RefreshTokenEntity();

        refreshTokenEntity.setId(body.get("session_state"));
        refreshTokenEntity.setRefreshToken(body.get("refresh_token"));

        refreshTokenRepo.save(refreshTokenEntity);

    }

    public void saveRefreshTokenForJunitTest(Map<String, String> jsonObject){
        saveRefreshToken(jsonObject);
    }

    private void updateRefresToken(Map body){

        Optional<RefreshTokenEntity> refreshTokenEntityOptional = refreshTokenRepo.findById((String)body.get("session_state"));
        RefreshTokenEntity refreshTokenEntity = refreshTokenEntityOptional.get();
        refreshTokenEntity.setRefreshToken((String)body.get("refresh_token"));
        refreshTokenRepo.save(refreshTokenEntity);

    }



    abstract ClientResponse getToken(MultiValueMap<String, String> body);

    abstract ClientResponse getUserInfo(String accessToken);

    abstract ClientResponse requestLogout(MultiValueMap<String, String> body);

    abstract String getRefreshTokenId(String accessToken);

}
