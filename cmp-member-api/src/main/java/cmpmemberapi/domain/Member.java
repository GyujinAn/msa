package cmpmemberapi.domain;



import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Member extends BaseTimeEntity {

    @Id
    @Column(name = "MEMBER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255, nullable = false)
    private String name;

    @Column(length = 255, nullable = false, unique = true)
    private String account;

    @Column(length = 255, nullable = false)
    private String password;

    @ManyToMany
    @Column
    @JoinTable(name = "MEMBER_ROLE",
            joinColumns = @JoinColumn(name = "MEMBER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLES_ID")
    )
    private List<Roles> roles = new ArrayList<>();

    @ManyToMany
    @Column
    @JoinTable(name = "MEMBER_ORGANIZATION",
            joinColumns = @JoinColumn(name = "MEMBER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ORGANIZATION_ID")
    )
    private List<Organization> organizations = new ArrayList<>();

    @ManyToMany
    @Column
    @JoinTable(name = "MEMBER_PROJECT",
            joinColumns = @JoinColumn(name = "MEMBER_ID"),
            inverseJoinColumns = @JoinColumn(name = "PROJECT_ID")
    )
    private List<Project> projects = new ArrayList<>();

    public Member() {
    }

    @Builder
    public Member(Long id, String name, String account, String password, List<Roles> roles, List<Organization> organizations, List<Project> projects) {
        this.id = id;
        this.name = name;
        this.account = account;
        this.password = password;
        this.roles = roles;
        this.organizations = organizations;
        this.projects = projects;
    }

}
