package team.lte.aclservice.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import team.lte.aclservice.entity.po.User;
import team.lte.aclservice.service.PermissionService;
import team.lte.aclservice.service.UserService;
import team.lte.security.entity.SecurityUser;

/**
 * 自定义userDetailsService - 认证用户详情。
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserService userService;

    @Resource
    private PermissionService permissionService;

    /**
     * 根据账号获取用户信息
     * 
     * @param username:
     * @return {@link org.springframework.security.core.userdetails.UserDetails}
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 从数据库中取出用户信息
        User user = userService.selectByName(username);

        // 判断用户是否存在
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在！");
        }
        // 返回UserDetails实现类
        team.lte.security.entity.User curUser = new team.lte.security.entity.User();
        BeanUtils.copyProperties(user, curUser);

        List<String> authorities = permissionService.listPermissionValuesByUserId(user.getId());
        SecurityUser securityUser = new SecurityUser(curUser);
        securityUser.setPermissionValueList(authorities);
        return securityUser;
    }

}
