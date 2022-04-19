package cmpmemberapi.configuration;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
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
@Data
@Profile("localwithkeycloak")
public class OIDCProperties {

    private String protocol;

    private String url;

    private String clientId;

    private String username;

    private String password;

    private String grantType;
}



