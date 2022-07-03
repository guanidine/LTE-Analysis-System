package team.lte.security.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import team.lte.commonutils.result.R;
import team.lte.commonutils.result.ResponseUtils;

/**
 * 未授权的统一处理方式。
 *
 * @author Guanidine Beryllium
 */
public class UnauthorizedEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException authException) {

        ResponseUtils.out(response, R.error().message("没有访问权限"));
    }
}
