package cloudserviceapi.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author gj.an@okestro.com
 * @since 2021/07/09
 */

@Entity
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) //사용하지말것
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Inheritance(strategy = InheritanceType.JOINED) //기본적으로 사용해야됨
@DiscriminatorColumn(name = "service_type")
@Getter
@Setter
public abstract class CloudService extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "providerId")
    private CloudProvider cloudProvider;


}

