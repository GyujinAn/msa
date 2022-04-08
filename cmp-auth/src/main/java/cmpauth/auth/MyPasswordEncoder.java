package cmpauth.auth;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author agj017@gmail.com
 * @since 2022/02/08
 */
public class MyPasswordEncoder {
    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.printf("admin : %s\n", passwordEncoder.encode("admin"));
        System.out.printf("user : %s\n", passwordEncoder.encode("user"));
        System.out.printf("bar : %s\n", passwordEncoder.encode("bar"));


    }

}
