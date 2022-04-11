package cmpmemberapi;

import cmpmemberapi.domain.Member;
import cmpmemberapi.repository.MemberRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.Assert.*;

/**
 * @author agj017@gmail.com
 * @since 2022/04/11
 */
public class MemberServiceImplTest {


    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void getMember() {
        //given
        Member admin = memberRepository.findByAccount("admin").get();


        //when
        Member member = memberService.getMember(admin.getId());


        //then




    }

    private void createMember(){
        
    }
    
    private void createOrganization(){

    }

    private void createProject(){

    }
    
    
}