package cmpmemberapi;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author agj017@gmail.com
 * @since 2022/02/08
 */
@RequiredArgsConstructor
@Component
public class Oauth2AuthenticationProvider {

    private final PasswordEncoder passwordEncoder;

    private final MemberRepository memberRepository;


}
