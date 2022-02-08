package cloudmanagementplatform.cloudauthorization;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author agj017@gmail.com
 * @since 2022/02/08
 */
public interface CloudMemberRepository extends JpaRepository<CloudMember, Long> {

    Optional<CloudMember> findByUid(String uid);
}
