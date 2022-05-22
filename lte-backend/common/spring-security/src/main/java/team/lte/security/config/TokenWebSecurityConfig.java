package team.lte.security.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import team.lte.security.filter.TokenAuthenticationFilter;
import team.lte.security.filter.TokenLoginFilter;
import team.lte.security.security.DefaultPasswordEncoder;
import team.lte.security.security.TokenLogoutHandler;
import team.lte.security.security.UnauthorizedEntryPoint;

/**
 * Security配置类。
 *
 * @author Guanidine Beryllium
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class TokenWebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final DefaultPasswordEncoder defaultPasswordEncoder;
    private final RedisTemplate<String, List<String>> redisTemplate;

    @Autowired
    public TokenWebSecurityConfig(UserDetailsService userDetailsService, DefaultPasswordEncoder defaultPasswordEncoder,
        RedisTemplate redisTemplate) {
        this.userDetailsService = userDetailsService;
        this.defaultPasswordEncoder = defaultPasswordEncoder;
        this.redisTemplate = redisTemplate;
    }

    /** 配置设置 */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling().authenticationEntryPoint(new UnauthorizedEntryPoint()).and().csrf().disable()
            .authorizeRequests().antMatchers("/v3/api-docs/**", "/swagger-ui/**", "/acl/index/register/**").permitAll()
            .anyRequest().authenticated().and().logout().logoutUrl("/acl/index/logout")
            .addLogoutHandler(new TokenLogoutHandler(redisTemplate)).and()
            .addFilter(new TokenLoginFilter(authenticationManager(), redisTemplate))
            .addFilter(new TokenAuthenticationFilter(authenticationManager(), redisTemplate)).httpBasic();
    }

    /** 密码处理 */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(defaultPasswordEncoder);
    }
}