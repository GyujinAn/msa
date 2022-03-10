package cloudserviceapi.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author gj.an@okestro.com
 * @since 2021/07/09
 */


@Entity
@DiscriminatorValue("Network")
public class Network extends CloudService {

}
