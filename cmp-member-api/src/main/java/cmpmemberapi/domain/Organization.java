package cmpmemberapi.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

/**
 * @author agj017@gmail.com
 * @since 2022/04/08
 */

@Entity
@Getter
public class Organization extends BaseTimeEntity{

    @Id
    @Column(name = "ORGANIZATION_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String name;

    @ManyToMany(mappedBy = "organizations")
    private List<Member> members;

    public Organization() {
    }

    @Builder
    public Organization(String name, List<Member> members) {
        this.name = name;
        this.members = members;
    }
}
