package cmpmemberapi.service;


import cmpmemberapi.dto.MemberDto;

public interface MemberService {

    Long saveMember(MemberDto memberDto) throws Exception;

    Long updateMember(String memberId, MemberDto memberDto);

    MemberDto findMember(String memberId);

    MemberDto findMembers();

    boolean deleteMember();

}

