package cloudmanagementplatform.cloudauth.member;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

/**
 * @author agj017@gmail.com
 * @since 2022/02/08
 */
@RepositoryRestResource(path = "members")
public interface CloudMemberRepository extends PagingAndSortingRepository<CloudMember, Long> {

    Optional<CloudMember> findByUsername(String username);

}
