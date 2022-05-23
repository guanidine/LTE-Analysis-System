package team.lte.security.security;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import team.lte.commonutils.Constants;
import team.lte.commonutils.IPUtils;
import team.lte.commonutils.jwt.JwtUtils;
import team.lte.commonutils.result.R;
import team.lte.commonutils.result.ResponseUtils;

/**
 * 登出业务逻辑类。
 *
 * @author Guanidine Beryllium
 */
public class TokenLogoutHandler implements LogoutHandler {

    private final RedisTemplate<String, List<String>> redisTemplate;

    public TokenLogoutHandler(RedisTemplate<String, List<String>> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String token = request.getHeader(Constants.TOKEN_HEADER);
        if (token != null) {
            // 清空当前用户缓存中的权限数据
            String userName = JwtUtils.getIdByToken(token);
            redisTemplate.delete(userName + IPUtils.getIpAddr(request));
        }
        ResponseUtils.out(response, R.ok());
    }

}