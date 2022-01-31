package cloudgateway.data;

import org.springframework.data.jpa.repository.JpaRepository;


public interface HttpMessageRepo extends JpaRepository<HttpMessageEntity, Long> {
}
