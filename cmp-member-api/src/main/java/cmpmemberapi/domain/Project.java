package cmpmemberapi.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Project extends BaseTimeEntity{
    @Id
    @Column(name = "PROJECT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String name;

    @ManyToMany(mappedBy = "projects")
    private List<Member> members;

    public Project() {
    }

    @Builder
    public Project(String name, List<Member> members) {
        this.name = name;
        this.members = members;
    }
}
