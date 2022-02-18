package cloudmanagementplatform.cloudauth;

import cloudmanagementplatform.cloudauth.member.CloudMember;
import cloudmanagementplatform.cloudauth.member.CloudMemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

/**
 * @author agj017@gmail.com
 * @since 2022/02/08
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CloudMemberRepositoryTest {

    @Autowired
    private CloudMemberRepository cloudMemberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void createCloudMember(){
//        cloudMemberRepository.save(CloudMember.builder()
//                .uid("user")
//                .password(passwordEncoder.encode("pass"))
//                .name("user")
//                .email("agj017@gmail.com")
//                .roles(Collections.singletonList("ROLE_USER"))
//                .build());


    }


}