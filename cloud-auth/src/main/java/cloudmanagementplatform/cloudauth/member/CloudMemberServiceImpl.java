package cloudmanagementplatform.cloudauth.member;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PostLoad;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author agj017@gmail.com
 * @since 2022/02/21
 */
@Service
@RequiredArgsConstructor
public class CloudMemberServiceImpl implements CloudMemberService {

//    private final CloudMemberRepository cloudMemberRepository;

    private final EntityManagerFactory entityManagerFactory;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<CloudMember> query = entityManager.createQuery("SELECT m from CloudMember m where m.username='"+s+"'", CloudMember.class);
        CloudMember cloudMember = query.getSingleResult();

//        CloudMember cloudMember = cloudMemberRepository.findByUsername(s);


        if(cloudMember == null) {
            throw new UsernameNotFoundException("User " + s + "not found");
        }

        return cloudMember;
    }

    @Override
    public Long save(CloudMember cloudMember) {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        cloudMember.setPassword(bCryptPasswordEncoder.encode(cloudMember.getPassword()));
        cloudMember.setUpdatedAt(LocalDateTime.now());
        cloudMember.setCreatedAt(LocalDateTime.now());

        return cloudMember.getId();
    }
}
