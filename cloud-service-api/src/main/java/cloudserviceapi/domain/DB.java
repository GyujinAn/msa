package cloudserviceapi.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author agj017@gmail.com
 * @since 2022/03/10
 */
@Entity
@DiscriminatorValue("DB")
public class DB extends CloudService{
}
