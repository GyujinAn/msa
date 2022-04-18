package cmpmemberapi;

import cmpmemberapi.domain.Member;
import cmpmemberapi.domain.Organization;
import cmpmemberapi.domain.Project;
import cmpmemberapi.repository.MemberRepository;
import cmpmemberapi.service.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

import static org.junit.Assert.*;
/**
 * @author agj017@gmail.com
 * @since 2022/04/11
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceImplTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void getMember() {
        //given



        //when


        //then




    }

    private void createMember(){
        
    }
    
    private void createOrganization(){

    }

    private void createProject(){

    }
    
    
}