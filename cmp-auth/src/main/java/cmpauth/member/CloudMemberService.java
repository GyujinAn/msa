package cmpauth.member;

import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author agj017@gmail.com
 * @since 2022/02/21
 */
public interface CloudMemberService  extends UserDetailsService {

    Long save(CloudMember cloudMember);

}
