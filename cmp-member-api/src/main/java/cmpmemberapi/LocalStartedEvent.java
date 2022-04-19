package cmpmemberapi;

import cmpmemberapi.domain.Member;
import cmpmemberapi.domain.Organization;
import cmpmemberapi.domain.Project;
import cmpmemberapi.repository.MemberRepository;
import cmpmemberapi.repository.OrganizationRepository;
import cmpmemberapi.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author agj017@gmail.com
 * @since 2022/04/11
 */

@Slf4j
@Component
@Profile("local")
@RequiredArgsConstructor
public class LocalStartedEvent implements ApplicationListener<ApplicationStartedEvent>{


    private final MemberRepository memberRepository;

    private final OrganizationRepository organizationRepository;

    private final ProjectRepository projectRepository;


    @Override
    @Transactional
    public void onApplicationEvent(ApplicationStartedEvent event) {

        log.info("stated local spring boot init ");



        Organization personal = Organization.builder()
                .name("개인")
                .build();

        personal.setCreatedDate(LocalDateTime.now());
        personal.setUpdatedDate(LocalDateTime.now());
        organizationRepository.save(personal);

        Project personalProject = Project.builder()
                .name("개인 프로젝트")
                .build();

        personalProject.setCreatedDate(LocalDateTime.now());
        personalProject.setUpdatedDate(LocalDateTime.now());
        projectRepository.save(personalProject);

        List<Organization> organizations = new ArrayList<>();

        organizations.add(personal);

        List<Project> projects = new ArrayList<>();

        projects.add(personalProject);

        Member admin = Member.builder()
                .account("admin")
                .password("no support")
                .name("cmp platform admin")
//                .memberRole(MemberRole123.PLATFORM_ADMIN)
                .organizations(organizations)
                .projects(projects)
                .build();

        admin.setCreatedDate(LocalDateTime.now());
        admin.setUpdatedDate(LocalDateTime.now());

        memberRepository.save(admin);

        log.info("ended local spring boot init ");
    }
}
