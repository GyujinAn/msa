package cmpmemberapi.controller;

import cmpmemberapi.domain.Member;
import cmpmemberapi.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author agj017@gmail.com
 * @since 2022/04/11
 */
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public Member getMember(String memberId){
        return memberService.getMember(memberId);
    }

}
