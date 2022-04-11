package cmpmemberapi.repository;

import cmpmemberapi.domain.Member;
import cmpmemberapi.domain.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author agj017@gmail.com
 * @since 2022/04/11
 */
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
}
