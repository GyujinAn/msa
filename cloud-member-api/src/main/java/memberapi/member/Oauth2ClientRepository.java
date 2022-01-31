package memberapi.member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Oauth2ClientRepository extends JpaRepository<Oauth2ClientEntity, Long> {

    Optional<Oauth2ClientEntity> findByEmail(String email);
}
