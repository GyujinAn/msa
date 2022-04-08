package cmpauth.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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

        Optional<CloudMember> byUsername = cloudMemberRepository.findByUsername(s);
        CloudMember cloudMember = byUsername.orElse(null);

        if(cloudMember == null) {
            throw new UsernameNotFoundException("User " + s + "not found");
        }

        return cloudMember;
    }

    @Override
    public Long save(CloudMember cloudMember) {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        cloudMember.setPassword(bCryptPasswordEncoder.encode(cloudMember.getPassword()));
        cloudMember.setUpdatedAt(LocalDateTime.now());
        cloudMember.setCreatedAt(LocalDateTime.now());

        return cloudMember.getId();
    }
}
