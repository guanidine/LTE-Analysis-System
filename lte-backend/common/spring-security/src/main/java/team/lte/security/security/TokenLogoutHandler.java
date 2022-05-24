package team.lte.security.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import team.lte.commonutils.result.R;
import team.lte.commonutils.result.ResponseUtils;

/**
 * 登出业务逻辑类。
 *
 * @author Guanidine Beryllium
 */
public class TokenLogoutHandler implements LogoutHandler {

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        ResponseUtils.out(response, R.ok());
    }

}