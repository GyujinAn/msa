package memberapi.member.service.impl;

import memberapi.member.model.entity.UserOld;
import memberapi.member.repository.UserRepository;
import memberapi.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author agj017@gmail.com
 * @since 2021/10/04
 */
@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements MemberService<UserOld> {


    private final UserRepository userRepository;

    @Override
    public UserOld get(Long id) {
        return null;
    }

    @Override
    public Long save(UserOld userOld) {

        UserOld save = userRepository.save(userOld);
        return save.getId();
    }

    @Override
    public Long update(UserOld userOld) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
