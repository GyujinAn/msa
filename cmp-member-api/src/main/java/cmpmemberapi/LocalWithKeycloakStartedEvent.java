package cmpmemberapi;

import cmpmemberapi.configuration.OIDCProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @author agj017@gmail.com
 * @since 2022/04/19
 */

@Slf4j
@Component
@Profile("localwithkeycloak")
@RequiredArgsConstructor
public class LocalWithKeycloakStartedEvent implements ApplicationListener<ApplicationStartedEvent> {

    private final OIDCProperties oidcProperties;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {


    }
}
