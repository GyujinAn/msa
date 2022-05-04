package portaluser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import portaluser.domain.CloudServiceCategory;

public interface CloudServiceCategoryRepository extends JpaRepository<CloudServiceCategory, Long> {

}
