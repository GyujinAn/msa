package memberapi.authorization;

import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author agj017@gmail.com
 * @since 2022/01/20
 */
public class KakaoOauth2 extends CmpOauth2{
    public KakaoOauth2(WebClient oauth2WebClient, RefreshTokenRepo refreshTokenRepo) {
        super(oauth2WebClient, refreshTokenRepo);
    }

    @Override
    ClientResponse getToken(MultiValueMap<String, String> body) {
        return null;
    }

    @Override
    ClientResponse getUserInfo(String accessToken) {
        return null;
    }

    @Override
    ClientResponse requestLogout(MultiValueMap<String, String> body) {
        return null;
    }

    @Override
    String getRefreshTokenId(String accessToken) {
        return null;
    }
}
