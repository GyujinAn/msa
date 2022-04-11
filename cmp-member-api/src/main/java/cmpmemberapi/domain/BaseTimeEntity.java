package cmpmemberapi.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * @author agj017@gmail.com
 * @since 2022/04/11
 */
@MappedSuperclass
@Setter
@Getter
public abstract class BaseTimeEntity {

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;
}
