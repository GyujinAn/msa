package cmpmemberapi.controller;

import cmpmemberapi.domain.Member;
import cmpmemberapi.dto.MemberDto;
import cmpmemberapi.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author agj017@gmail.com
 * @since 2022/04/11
 */
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public Long createMember(@RequestBody MemberDto memberDto){

        return 0L;
    }

    @PutMapping("/{memberId}")
    public Long updateMember(@PathVariable("memberId") Long memberId, @RequestBody MemberDto memberDto){
        return 0L;
    }

    @GetMapping("/{memberId}")
    public MemberDto readMember(@PathVariable("memberId") Long memberId){
        return null;
    }

    @GetMapping
    public List<MemberDto> readMemberList(){
        return null;
    }

    @DeleteMapping
    public void deleteMember(){

    }


}
