package cmpserviceapi.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author gj.an@okestro.com
 * @since 2021/07/09
 */


@Entity
@DiscriminatorValue("Computing")
public class Computing extends CloudService {

}
