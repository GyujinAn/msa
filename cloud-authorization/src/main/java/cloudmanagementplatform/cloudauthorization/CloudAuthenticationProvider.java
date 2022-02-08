package cloudmanagementplatform.cloudauthorization;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author agj017@gmail.com
 * @since 2022/02/08
 */

@Component
public class CloudAuthenticationProvider implements AuthenticationProvider {

    private final PasswordEncoder passwordEncoder;

    private final CloudMemberRepository cloudMemberRepository;

    public CloudAuthenticationProvider(CloudMemberRepository cloudMemberRepository) {
        this.cloudMemberRepository = cloudMemberRepository;
        this.passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    }

    @Override
    public Authentication authenticate(Authentication authentication) {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        CloudMember cloudMember = cloudMemberRepository.findByUid(name).orElseThrow(() -> new UsernameNotFoundException("user is not exists"));
        if(!passwordEncoder.matches(password, cloudMember.getPassword()))
            throw new BadCredentialsException("password is not valid");


        return new UsernamePasswordAuthenticationToken(name, password, cloudMember.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
