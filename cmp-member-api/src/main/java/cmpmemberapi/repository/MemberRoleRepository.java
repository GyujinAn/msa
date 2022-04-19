package cmpmemberapi.repository;


import cmpmemberapi.domain.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author agj017@gmail.com
 * @since 2022/04/19
 */
public interface MemberRoleRepository extends JpaRepository<Roles, String> {
}
