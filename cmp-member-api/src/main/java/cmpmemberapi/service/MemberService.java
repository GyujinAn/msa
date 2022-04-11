package cmpmemberapi.service;


import cmpmemberapi.MemberDto;
import cmpmemberapi.domain.Member;

public interface MemberService {
    Long save(MemberDto memberDto);

    Member getMember(String memberId);
}
