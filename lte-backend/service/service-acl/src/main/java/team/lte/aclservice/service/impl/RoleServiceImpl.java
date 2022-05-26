package team.lte.aclservice.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import team.lte.aclservice.entity.po.Role;
import team.lte.aclservice.entity.po.UserRole;
import team.lte.aclservice.mapper.RoleMapper;
import team.lte.aclservice.service.RoleService;
import team.lte.aclservice.service.UserRoleService;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author lte
 * @since 2022-04-28
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Resource
    private UserRoleService userRoleService;

    @Override
    public Map<String, Object> findRolesByUserId(Long userId) {
        List<Role> allRolesList = baseMapper.selectList(null);

        List<UserRole> existUserRoleList = userRoleService
            .list(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserId, userId).select(UserRole::getRoleId));

        List<Long> existRoleList = existUserRoleList.stream().map(UserRole::getRoleId).collect(Collectors.toList());

        List<Role> assignRoles = new ArrayList<>();
        for (Role role : allRolesList) {
            if (existRoleList.contains(role.getId())) {
                assignRoles.add(role);
            }
        }

        Map<String, Object> roleMap = new HashMap<>();
        roleMap.put("assignRoles", assignRoles);
        roleMap.put("allRolesList", allRolesList);
        return roleMap;
    }

    @Override
    public boolean assignRolesByUserId(Long userId, List<Long> roleIds) {

        List<UserRole> userRoleList = new ArrayList<>();
        List<UserRole> userRoleRemoveList = new ArrayList<>();
        List<UserRole> origin = userRoleService.getBaseMapper()
            .selectList(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserId, userId));
        for (UserRole userRole : origin) {
            Long roleId = userRole.getRoleId();
            if (roleIds.contains(roleId)) {
                roleIds.remove(roleId);
            } else {
                userRoleRemoveList.add(userRole);
            }
        }
        for (Long roleId : roleIds) {
            if (roleId == null) {
                continue;
            }
            UserRole userRole = new UserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);
            userRoleList.add(userRole);
        }
        // 垃圾国产之光，抄Postgres的时候，为什么人家可以的功能你不行
        boolean flag = true;
        if (userRoleList.size() == 1) {
            flag = userRoleService.save(userRoleList.get(0));
        } else if (userRoleList.size() > 1) {
            flag = userRoleService.saveBatch(userRoleList);
        }
        if (userRoleRemoveList.size() == 1) {
            flag = userRoleService.removeById(userRoleRemoveList.get(0));
        } else if (userRoleRemoveList.size() > 1) {
            flag = userRoleService.removeBatchByIds(userRoleRemoveList);
        }
        return flag;
    }

    @Override
    public List<Role> listRolesByUserId(Long userId) {
        List<UserRole> userRoleList = userRoleService
            .list(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserId, userId).select(UserRole::getRoleId));
        List<Long> roleIdList = userRoleList.stream().map(UserRole::getRoleId).collect(Collectors.toList());
        List<Role> roleList = new ArrayList<>();
        if (!roleIdList.isEmpty()) {
            roleList = getBaseMapper().selectBatchIds(roleIdList);
        }
        return roleList;
    }

}
