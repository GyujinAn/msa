package cmpapigateway.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Component
public class JwtTokenHandler {

    @Value("${oauth.public-key}")
    private String rsaPublicKey;

    public boolean isJwtValid(String token){

        try {
            validateAndParseJwt(token);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return false;
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public String getUsername(String token){
        return getUsername0(token);
    }

    public String getUsername(HttpHeaders headers){
        String accessTokenFromHeader = getAccessTokenFromHeader(headers);
        return getUsername0(accessTokenFromHeader);
    }

    public String getAccessTokenFromHeader(HttpHeaders headers){

        String accessToken = headers.getFirst("Authorization");
        String[] arr = accessToken.split(" ");
        accessToken = arr[1];

        return accessToken;
    }


    private String getUsername0(String token){
        String username;
        Jws<Claims> jwt;
        try {
            jwt = validateAndParseJwt(token);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "no username";
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
            return "no username";
        } catch (Exception e){
            e.printStackTrace();
            return "no username";
        }
        username = (String)jwt.getBody()
                .get("preferred_username");

        if(username == null || username.equals("")){
            return "no username";
        }

        return username;
    }


    private Jws<Claims> validateAndParseJwt(String token) throws NoSuchAlgorithmException, InvalidKeySpecException {

        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(rsaPublicKey));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);


        Jws<Claims> jwt = Jwts.parserBuilder()
                .setSigningKey(publicKey)
                .build()
                .parseClaimsJws(token);

        return jwt;
    }


}
