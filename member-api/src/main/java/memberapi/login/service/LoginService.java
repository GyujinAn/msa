package memberapi.login.service;

import memberapi.login.model.LoginVo;
import memberapi.member.model.entity.Admin;

/**
 * @author agj017@gmail.com
 * @since 2021/09/17
 */
public interface LoginService {

    Admin loginAdmin(LoginVo loginVo);
}
