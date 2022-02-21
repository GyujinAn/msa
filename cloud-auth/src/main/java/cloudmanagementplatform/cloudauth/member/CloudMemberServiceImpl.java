package cloudmanagementplatform.cloudauth.member;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.PostLoad;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author agj017@gmail.com
 * @since 2022/02/21
 */
@Service
@RequiredArgsConstructor
public class CloudMemberServiceImpl implements CloudMemberService {

    private final CloudMemberRepository cloudMemberRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Optional<CloudMember> byLoginId = cloudMemberRepository.findByLoginId(s);
        CloudMember cloudMember = byLoginId.orElse(null);

        return new User(cloudMember.getLoginId(), cloudMember.getLoginPw(), null);
    }

    @Override
    public Long save(CloudMember cloudMember) {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        cloudMember.setLoginPw(bCryptPasswordEncoder.encode(cloudMember.getLoginPw()));
        cloudMember.setUpdatedAt(LocalDateTime.now());
        cloudMember.setCreatedAt(LocalDateTime.now());

        return cloudMember.getId();
    }
}
