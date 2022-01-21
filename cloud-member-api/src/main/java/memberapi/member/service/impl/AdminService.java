package memberapi.member.service.impl;

import memberapi.member.model.entity.Admin;
import memberapi.member.service.MemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author agj017@gmail.com
 * @since 2021/10/04
 */
@Service
@Transactional
public class AdminService implements MemberService<Admin> {

    @Override
    public Admin get(Long id) {
        return null;
    }

    @Override
    public Long save(Admin admin) {
        return null;
    }

    @Override
    public Long update(Admin admin) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
