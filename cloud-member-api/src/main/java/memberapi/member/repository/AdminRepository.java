package memberapi.member.repository;

import memberapi.member.model.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author agj017@gmail.com
 * @since 2021/10/04
 */
public interface AdminRepository extends JpaRepository<Admin, Long> {
}
