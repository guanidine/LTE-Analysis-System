package team.lte.security.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import lombok.extern.slf4j.Slf4j;
import team.lte.commonutils.Constants;
import team.lte.commonutils.jwt.JwtUtils;
import team.lte.commonutils.result.R;
import team.lte.commonutils.result.ResponseUtils;

/**
 * 访问过滤器。
 *
 * @author Guanidine Beryllium
 */
@Slf4j
public class TokenAuthenticationFilter extends BasicAuthenticationFilter {
    public TokenAuthenticationFilter(AuthenticationManager authManager) {
        super(authManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
        throws IOException, ServletException {
        log.debug("================={}", req.getRequestURI());

        if (req.getRequestURI().contains("/swagger-ui") || req.getRequestURI().contains("/v3/api-docs")
            || req.getRequestURI().contains("/druid") || req.getRequestURI().contains("/favicon.ico")
            || req.getRequestURI().contains("/acl/index/register")) {
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
        if (authentication != null) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            ResponseUtils.out(res, R.error().message("登录过期，请重新登录"));
        }

        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(Constants.TOKEN_HEADER);
        if (token != null && !"".equals(token.trim())) {
            String userName = JwtUtils.getIdByToken(token);

            Collection<GrantedAuthority> authorities = new ArrayList<>();

            if (!StringUtils.isEmpty(userName)) {
                return new UsernamePasswordAuthenticationToken(userName, token, authorities);
            }
            return null;
        }
        return null;
    }
}