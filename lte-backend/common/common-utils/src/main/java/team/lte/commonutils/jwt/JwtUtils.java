package team.lte.commonutils.jwt;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtils {

    public static final long EXPIRE = 86400000;
    public static final String APP_SECRET = "B1NnB0zfFj2nygkTkAuxIMlIxBqJX9N3";

    public static String getJwtToken(String id, String nickname) {

        return Jwts.builder().setHeaderParam("typ", "JWT").setHeaderParam("alg", "HS256").setSubject("user")
            .setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + EXPIRE)).claim("id", id)
            .claim("nickname", nickname).signWith(SignatureAlgorithm.HS256, APP_SECRET).compact();
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
        String jwtToken = request.getHeader("token");
        if (StringUtils.isEmpty(jwtToken)) {
            return false;
        }
        Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        return true;
    }

    /** 根据token获取用户id */
    public static String getIdByToken(HttpServletRequest request) {
        String jwtToken = request.getHeader("X-Token");
        if (StringUtils.isEmpty(jwtToken)) {
            return "";
        }
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        Claims claims = claimsJws.getBody();
        return (String)claims.get("id");
    }
}