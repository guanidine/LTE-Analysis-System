package team.lte.security.filter;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

import team.lte.commonutils.jwt.JwtUtils;
import team.lte.commonutils.result.R;
import team.lte.commonutils.result.ResponseUtils;
import team.lte.security.entity.SecurityUser;
import team.lte.security.entity.User;

/**
 * 登录过滤器。继承 {@link UsernamePasswordAuthenticationFilter}，对用户名密码进行登录校验。
 *
 * @author Guanidine Beryllium
 */
public class TokenLoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public TokenLoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        this.setPostOnly(false);
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/acl/login", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
        throws AuthenticationException {
        try {
            User user = new ObjectMapper().readValue(req.getInputStream(), User.class);

            return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword(), new ArrayList<>()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /** 登录成功 */
    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
        Authentication auth) {
        SecurityUser user = (SecurityUser)auth.getPrincipal();
        String token = JwtUtils.getJwtToken(user.getCurrentUserInfo().getName());

        ResponseUtils.out(res, R.ok().data("token", token));
    }

    /** 登录失败 */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException e) {
        ResponseUtils.out(response, R.error());
    }
}
