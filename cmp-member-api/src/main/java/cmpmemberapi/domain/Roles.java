package cmpmemberapi.domain;

import lombok.Builder;
import org.springframework.context.annotation.Bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

/**
 * @author agj017@gmail.com
 * @since 2022/04/19
 */

@Entity
public class Roles {

    @Id
    @Column(name = "ROLES_ID")
    private String id;

    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<Member> members;

    @Builder
    public Roles(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Roles() {

    }
}
