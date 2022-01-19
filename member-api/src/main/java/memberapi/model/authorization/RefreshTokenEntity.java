package memberapi.model.authorization;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name = "KEYCLOCK_REFRESH_TOKEN", catalog = "ECP_ADMIN")
public class RefreshTokenEntity {

    @Id
    @Column(name = "SID")
    private String id;

    @Column(name = "REFRESH_TOKEN")
    private String refreshToken;

    @Column(name="CREATED_AT", length = 10000)
    private LocalDateTime createdAt;

    @Column(name="UPDATED_AT")
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }


}
