package memberapi.member.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author agj017@gmail.com
 * @since 2021/09/15
 */

@Entity
@Getter
@Setter
public class Division {
    @Id
    @GeneratedValue
    @Column(name = "division_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "id")
    private List<Member> members = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Division parent;

    @OneToMany(mappedBy = "parent")
    private List<Division> child = new ArrayList<>();


}
