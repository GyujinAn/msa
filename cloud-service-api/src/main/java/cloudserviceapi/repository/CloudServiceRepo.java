package cloudserviceapi.repository;

import cloudserviceapi.domain.CloudService;
import org.springframework.data.repository.CrudRepository;

/**
 * @author agj017@gmail.com
 * @since 2022/03/10
 */
public interface CloudServiceRepo extends CrudRepository<CloudService, Long> {
}
