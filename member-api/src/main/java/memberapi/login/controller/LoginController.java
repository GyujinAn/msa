package memberapi.login.controller;

import memberapi.login.model.LoginVo;
import memberapi.login.service.LoginService;
import memberapi.member.model.entity.Admin;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author agj017@gmail.com
 * @since 2021/09/17
 */

@Slf4j
@RequiredArgsConstructor
@RestController
public class LoginController {


    private final member.LoginService loginService;


    @PostMapping("/admin/login")
    public String login(@RequestBody member.LoginVo loginVo, HttpSession httpSession){


        Admin admin = loginService.loginAdmin(loginVo);

        if(admin == null){
            return "failed";
        }

        httpSession.setAttribute("lgoinId", admin.getLoginId());

        return "succeeded";
    }

    @GetMapping("/checkLogin")
    public String checkLogin(HttpSession httpSession){

        return "checkLogin";

//        if(httpSession == null){
//            return "null";
//        }
//        return httpSession.getAttribute("lgoinId").toString();

    }
}
