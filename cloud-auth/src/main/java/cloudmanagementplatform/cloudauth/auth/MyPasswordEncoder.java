package cloudmanagementplatform.cloudauth.auth;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author agj017@gmail.com
 * @since 2022/02/08
 */
public class MyPasswordEncoder {
    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        System.out.printf("bar : %s\n", passwordEncoder.encode("bar"));
        System.out.printf("pass : %s\n", passwordEncoder.encode("pass"));
    }

}
