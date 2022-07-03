package team.lte.commonutils.jwt;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import team.lte.commonutils.Constants;

public class JwtUtils {

    public static final long EXPIRE = 86400000;
    public static final String APP_SECRET = "B1NnB0zfFj2nygkTkAuxIMlIxBqJX9N3";

    public static String getJwtToken(String username) {

        return Jwts.builder().setHeaderParam("typ", "JWT").setHeaderParam("alg", "HS256").setSubject(username)
            .setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
            .signWith(SignatureAlgorithm.HS256, APP_SECRET).compact();
    }

    /** 判断token是否存在与有效 */
    public static boolean checkToken(String jwtToken) {
        if (StringUtils.isEmpty(jwtToken)) {
            return false;
        }
        Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        return true;
    }

    /** 判断token是否存在与有效 */
    public static boolean checkToken(HttpServletRequest request) {
        return checkToken(request.getHeader(Constants.TOKEN_HEADER));
    }

    /** 根据token获取用户id */
    public static String getIdByToken(String jwtToken) {
        if (StringUtils.isEmpty(jwtToken)) {
            return "";
        }
        return Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken).getBody().getSubject();
    }

    /** 根据token获取用户id */
    public static String getIdByToken(HttpServletRequest request) {
        return getIdByToken(request.getHeader(Constants.TOKEN_HEADER));
    }
}