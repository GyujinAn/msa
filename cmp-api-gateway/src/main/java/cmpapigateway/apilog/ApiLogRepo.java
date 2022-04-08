package cmpapigateway.apilog;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ApiLogRepo extends JpaRepository<ApiLog, Long> {
}
