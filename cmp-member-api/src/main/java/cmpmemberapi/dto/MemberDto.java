package cmpmemberapi.dto;

import cmpmemberapi.domain.Member;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MemberDto {
    private Long id;

    private String name;

    private String account;

    private String password;

}
