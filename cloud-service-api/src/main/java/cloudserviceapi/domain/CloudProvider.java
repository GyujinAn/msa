package cloudserviceapi.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @author agj017@gmail.com
 * @since 2022/03/10
 */
@Entity
@Getter
@Setter
public class CloudProvider extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

}
