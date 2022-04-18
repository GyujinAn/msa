package cmpmemberapi.service;

import cmpmemberapi.domain.Member;
import cmpmemberapi.dto.MemberDto;
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
    public Long saveMember(MemberDto memberDto) {
        return null;
    }

    @Override
    public Long updateMember(String memberId, MemberDto memberDto) {
        return null;
    }

    @Override
    public MemberDto findMember(String memberId) {
        Optional<Member> byId = memberRepository.findById(Long.parseLong(memberId));

        return null;
    }

    @Override
    public MemberDto findMembers() {
        return null;
    }

    @Override
    public boolean deleteMember() {
        return false;
    }
}
