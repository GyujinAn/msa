package cloudmemberapi;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MemberTO {
    private Long id;

    private String name;

    private String account;

    private String password;

    private LocalDateTime lastAccessDt;

    private LocalDateTime regDt;

    public Member toEntity() {
        return new Member(id, name, account, password);
    }

}
