package memberapi.member.repository;


import memberapi.member.model.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author agj017@gmail.com
 * @since 2021/10/04
 */
public interface ProviderRepository extends JpaRepository<Provider, Long> {
}
