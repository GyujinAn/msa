//package cmpmemberapi;
//
//import cmpmemberapi.domain.Member;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class MemberServiceWithSecurity implements MemberService, UserDetailsService{
//
//    private final MemberRepository memberRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
//        Optional<Member> memberEntityWrapper = memberRepository.findByAccount(account);
//        Member memberEntity = memberEntityWrapper.orElse(null);
//
//        List<GrantedAuthority> authorities = new ArrayList<>();
//
//        authorities.add(new SimpleGrantedAuthority("ROLE_MEMBER"));
//
//        return new User(memberEntity.getAccount(), memberEntity.getPassword(), authorities);
//
//    }
//
//    @Transactional
//    @Override
//    public Long save(MemberDto memberDto) {
//        Member member = memberDto.toEntity();
//        member.setCreatedAt(LocalDateTime.now());
//        member.setUpdatedAt(LocalDateTime.now());
//
//        // 비밀번호 암호화
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        member.setPassword(passwordEncoder.encode(member.getPassword()));
//        return memberRepository.save(member).getId();
//
//    }
//}
