package cmpmemberapi;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberService extends UserDetailsService {
    Long save(MemberTO memberTO);
}
