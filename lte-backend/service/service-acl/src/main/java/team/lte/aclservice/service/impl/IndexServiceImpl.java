package team.lte.aclservice.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import team.lte.aclservice.entity.po.Role;
import team.lte.aclservice.entity.po.User;
import team.lte.aclservice.entity.vo.UserVO;
import team.lte.aclservice.service.IndexService;
import team.lte.aclservice.service.PermissionService;
import team.lte.aclservice.service.RoleService;
import team.lte.aclservice.service.UserService;
import team.lte.commonutils.encryption.MD5;
import team.lte.servicebase.exception.LteException;

@Service
public class IndexServiceImpl implements IndexService {
    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @Resource
    private PermissionService permissionService;

    @Override
    public Map<String, Object> getUserInfo(String username) {
        Map<String, Object> result = new HashMap<>(4);
        User user = userService.selectByName(username);

        List<Role> roleList = roleService.listRolesByUserId(user.getId());
        List<String> roleNameList = roleList.stream().map(Role::getName).collect(Collectors.toList());
        if (roleNameList.isEmpty()) {
            roleNameList.add("");
        }

        List<String> permissionValueList = permissionService.listPermissionValuesByUserId(user.getId());

        result.put("name", user.getName());
        result.put("avatar", user.getAvatar());
        result.put("roles", roleNameList);
        result.put("permissionValueList", permissionValueList);
        return result;
    }

    @Override
    public List<JSONObject> getAsyncRoutes(String username) {
        User user = userService.selectByName(username);

        return permissionService.listPermissionsByUserId(user.getId());
    }

    @Override
    public void register(UserVO userVO) {
        String name = userVO.getName();
        String password = userVO.getPassword();

        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(password)) {
            throw new LteException(20001, "用户名和密码不能为空");
        }
        Long count = userService.getBaseMapper().selectCount(new LambdaQueryWrapper<User>().eq(User::getName, name));
        if (count.intValue() > 0) {
            throw new LteException(20001, "该用户已注册");
        }

        User member = new User();
        member.setName(name);
        member.setPassword(MD5.encode(password));
        member.setIsDisabled(0);
        member.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        userService.save(member);
    }

}
