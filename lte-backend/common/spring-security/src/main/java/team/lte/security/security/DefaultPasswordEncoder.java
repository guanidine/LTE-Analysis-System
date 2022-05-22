package team.lte.security.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import team.lte.commonutils.encryption.MD5;

/**
 * 密码的处理方法类。
 *
 * @author Guanidine Beryllium
 */
@Component
public class DefaultPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return MD5.encode(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(MD5.encode(rawPassword.toString()));
    }
}