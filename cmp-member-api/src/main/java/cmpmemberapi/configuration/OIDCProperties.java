package cmpmemberapi.configuration;

import lombok.Getter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @author agj017@gmail.com
 * @since 2022/04/15
 */
@ConfigurationProperties(prefix = "oidc")
@Component
@Getter
@ToString
@Profile("localwithkeycloak")
public class OIDCProperties {

    String protocol;

    String url;

    String clientId;

    String username;

    String password;

    String grantType;
}
