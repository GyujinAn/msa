package cmpmemberapi;

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

    private MemberRepository memberRepository;

    @Override
    public Long save(MemberDto memberDto) {
        return null;
    }

    @Override
    public Member getMember(Long memberId) {

        Optional<Member> byId = memberRepository.findById(memberId);

        return byId.get();
    }


}
