package portaluser.initialization;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import portaluser.domain.CloudServiceCategory;
import portaluser.repository.CloudServiceCategoryRepository;

@Profile("local")
@Component
@RequiredArgsConstructor
public class LocalInitialization implements ApplicationListener<ApplicationStartedEvent> {

    private final CloudServiceCategoryRepository cloudServiceCategoryRepository;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        CloudServiceCategory cloudServiceCategory = new CloudServiceCategory();

    }
}
