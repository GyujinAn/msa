package memberapi.model.authorization;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepo extends JpaRepository<RefreshTokenEntity, String> {
}
