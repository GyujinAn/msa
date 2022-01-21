package memberapi.authorization;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.Assert.*;

/**
 * @author agj017@gmail.com
 * @since 2022/01/20
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("local")
public class KeycloackOauth2Test {

    @Autowired
    private CmpOauth2 keycloackOauth2;

    @Autowired
    WebClient oauth2WebClient;

    @Value("${cloudplatform.oauth2.keyclock.host}")
    private String host;

    @Value("${cloudplatform.oauth2.keyclock.realm}")
    private String realm;

    @Value("${cloudplatform.oauth2.keyclock.client_id}")
    private String clientId;

    @Value("${cloudplatform.oauth2.keyclock.client_secret}")
    private String clientSecret;

    @Test
    public void login() {

        String id = "Authtestuser";
        String pw = "okestro2018";

        CmpOauth2Response login = keycloackOauth2.login(id, pw);

        Assertions.assertEquals(login.getState(), CmpOauth2Response.State.NoContent);

    }


    @Test
    public void validateUser0() {

        String id = "Authtestuser";
        String pw = "okestro2018";
        CmpOauth2Response login = keycloackOauth2.login(id, pw);
        Assertions.assertEquals(login.getState(), CmpOauth2Response.State.NoContent);

        CmpOauth2Response cmpOauth2Response = keycloackOauth2.validateUser(login.getAccessToken());

        Assertions.assertEquals(cmpOauth2Response.getState(), CmpOauth2Response.State.OK);

    }


//    @Test
//    public void validateUser1() {
//
//        String id = "Authtestuser";
//        String pw = "okestro2018";
//        long betweenAccessAndRefresh = 1000 * 60 * 5;
//        CmpOauth2Response login = keycloackOauth2.login(id, pw);
//        Assertions.assertEquals(login.getState(), CmpOauth2Response.State.NoContent);
//        try {
//            Thread.sleep(betweenAccessAndRefresh);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        CmpOauth2Response cmpOauth2Response = keycloackOauth2.validateUser(login.getAccessToken());
//
//        Assertions.assertEquals(cmpOauth2Response.getState(), CmpOauth2Response.State.NoContent);
//
//    }
//
//    @Test
//    public void validateUser2() {
//
//        String id = "Authtestuser";
//        String pw = "okestro2018";
//        long afterRefresh = 1000 * 60 * 12;
//        CmpOauth2Response login = keycloackOauth2.login(id, pw);
//        Assertions.assertEquals(login.getState(), CmpOauth2Response.State.NoContent);
//        try {
//            Thread.sleep(afterRefresh);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        CmpOauth2Response cmpOauth2Response = keycloackOauth2.validateUser(login.getAccessToken());
//
//        Assertions.assertEquals(cmpOauth2Response.getState(), CmpOauth2Response.State.Unauthorized);
//
//    }

    @Test
    public void logout() {

        String id = "Authtestuser";
        String pw = "okestro2018";
        CmpOauth2Response login = keycloackOauth2.login(id, pw);
        Assertions.assertEquals(login.getState(), CmpOauth2Response.State.NoContent);

        keycloackOauth2.logout(login.getAccessToken());

    }

}