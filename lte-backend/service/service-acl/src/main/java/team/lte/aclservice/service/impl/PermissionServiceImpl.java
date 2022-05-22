package team.lte.aclservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import team.lte.aclservice.entity.po.Permission;
import team.lte.aclservice.entity.po.RolePermission;
import team.lte.aclservice.entity.po.User;
import team.lte.aclservice.mapper.PermissionMapper;
import team.lte.aclservice.service.PermissionService;
import team.lte.aclservice.service.RolePermissionService;
import team.lte.aclservice.service.UserService;
import team.lte.aclservice.util.PermissionUtils;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author lte
 * @since 2022-04-28
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Resource
    private RolePermissionService rolePermissionService;

    @Resource
    private UserService userService;

    @Override
    public List<Permission> listPermissions() {
        LambdaQueryWrapper<Permission> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Permission::getId);
        List<Permission> permissionList = getBaseMapper().selectList(wrapper);

        return PermissionUtils.listPermissionsReverse(permissionList);
    }

    @Override
    public List<Permission> listPermissionsByRoleId(Long roleId) {
        List<Permission> allPermissionList =
            getBaseMapper().selectList(new LambdaQueryWrapper<Permission>().orderByAsc(Permission::getId));
        List<RolePermission> rolePermissionList =
            rolePermissionService.list(new LambdaQueryWrapper<RolePermission>().eq(RolePermission::getRoleId, roleId));

        for (Permission permission : allPermissionList) {
            for (RolePermission rolePermission : rolePermissionList) {
                if (rolePermission.getPermissionId().equals(permission.getId())) {
                    permission.setSelect(true);
                }
            }
        }

        return PermissionUtils.listPermissionsReverse(allPermissionList);
    }

    @Override
    public boolean assignPermissionsByRoleId(Long roleId, List<Long> permissionIds) {
        // XXX: 慢死了，垃圾ORM
        List<RolePermission> rolePermissionList = new ArrayList<>();
        List<RolePermission> rolePermissionRemoveList = new ArrayList<>();
        List<RolePermission> origin = rolePermissionService.getBaseMapper()
            .selectList(new LambdaQueryWrapper<RolePermission>().eq(RolePermission::getRoleId, roleId));
        for (RolePermission rolePermission : origin) {
            Long permissionId = rolePermission.getPermissionId();
            if (permissionIds.contains(permissionId)) {
                permissionIds.remove(permissionId);
            } else {
                rolePermissionRemoveList.add(rolePermission);
            }
        }
        for (Long permissionId : permissionIds) {
            if (permissionId == null) {
                continue;
            }
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(permissionId);
            rolePermissionList.add(rolePermission);
        }
        boolean flag = rolePermissionList.isEmpty() || rolePermissionService.saveBatch(rolePermissionList);
        flag &= rolePermissionRemoveList.isEmpty() || rolePermissionService.removeBatchByIds(rolePermissionRemoveList);
        return flag;
    }

    @Override
    public boolean removePermissionsById(Long id) {
        List<Long> idList = this.listChildrenReverse(id);
        idList.add(id);
        getBaseMapper().deleteBatchIds(idList);
        return true;
    }

    @Override
    public List<String> listPermissionValuesByUserId(Long userId) {
        if (this.isAdmin(userId)) {
            return getBaseMapper().listPermissionValues();
        } else {
            return getBaseMapper().listPermissionValuesByUserId(userId);
        }
    }

    @Override
    public List<JSONObject> listPermissionsByUserId(Long userId) {
        List<Permission> selectPermissionList;
        if (this.isAdmin(userId)) {
            selectPermissionList = getBaseMapper().selectList(null);
        } else {
            selectPermissionList = getBaseMapper().listPermissionsByUserId(userId);
        }

        List<Permission> permissionList = PermissionUtils.listPermissionsReverse(selectPermissionList);
        return PermissionUtils.buildRouterPermissions(permissionList);
    }

    /** 判断是否为管理员 */
    private boolean isAdmin(Long userId) {
        // TODO: 其实更合适的做法是roleService中判断role是否为admin
        User user = userService.getById(userId);

        return user != null && "admin".equalsIgnoreCase(user.getName());
    }

    /** 递归获取子节点 */
    private List<Long> listChildrenReverse(Long id) {
        List<Permission> childList = getBaseMapper()
            .selectList(new LambdaQueryWrapper<Permission>().eq(Permission::getPid, id).select(Permission::getId));
        List<Long> idList = new ArrayList<>();
        childList.forEach(item -> {
            idList.add(item.getId());
            idList.addAll(this.listChildrenReverse(item.getId()));
        });
        return idList;
    }
}
