package memberapi.member.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author agj017@gmail.com
 * @since 2021/09/14
 */


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public abstract class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String loginId;

    private String loginPw;

    private String name;

    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    @Enumerated(EnumType.STRING)
    private MemberRole role;

    private LocalDateTime orderData;

//    @ManyToOne
//    @JoinColumn(name="division_id")
//    private Division division;



}
