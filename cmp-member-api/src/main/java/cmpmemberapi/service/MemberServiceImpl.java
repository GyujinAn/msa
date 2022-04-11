package cmpmemberapi.service;

import cmpmemberapi.MemberDto;
import cmpmemberapi.domain.Member;
import cmpmemberapi.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author agj017@gmail.com
 * @since 2022/04/11
 */
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public Long save(MemberDto memberDto) {
        return null;
    }

    @Override
    public Member getMember(String memberId) {

        Optional<Member> byId = memberRepository.findById(Long.parseLong(memberId));

        return byId.get();
    }


}
