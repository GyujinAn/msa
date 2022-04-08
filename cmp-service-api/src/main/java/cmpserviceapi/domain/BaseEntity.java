package cmpserviceapi.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * @author gj.an@okestro.com
 * @since 2021/07/09
 */


@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {
    private String createdBy;
    private LocalDateTime createdDate;
    private String lastModifedBy;
    private LocalDateTime lastModifiedDate;

}
