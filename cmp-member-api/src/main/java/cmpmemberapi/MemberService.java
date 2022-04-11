package cmpmemberapi;


import cmpmemberapi.domain.Member;

public interface MemberService {
    Long save(MemberDto memberDto);

    Member getMember(Long memberId);
}
