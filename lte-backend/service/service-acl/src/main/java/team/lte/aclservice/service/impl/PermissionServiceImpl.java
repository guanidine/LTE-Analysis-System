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
import team.lte.aclservice.mapper.PermissionMapper;
import team.lte.aclservice.service.PermissionService;
import team.lte.aclservice.service.RolePermissionService;

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

    @Override
    public List<Permission> listPermissions() {
        LambdaQueryWrapper<Permission> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Permission::getId);
        List<Permission> permissionList = getBaseMapper().selectList(wrapper);

        return listPermissionsReverse(getBaseMapper().getRootPermission(), permissionList);
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

        return listPermissionsReverse(getBaseMapper().getRootPermission(), allPermissionList);
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
        // 垃圾国产之光，抄Postgres的时候，为什么人家可以的功能你不行
        boolean flag = true;
        if (rolePermissionList.size() == 1) {
            flag = rolePermissionService.save(rolePermissionList.get(0));
        } else if (rolePermissionList.size() > 1) {
            flag = rolePermissionService.saveBatch(rolePermissionList);
        }
        if (rolePermissionRemoveList.size() == 1) {
            flag = rolePermissionService.removeById(rolePermissionRemoveList.get(0));
        } else if (rolePermissionRemoveList.size() > 1) {
            flag = rolePermissionService.removeBatchByIds(rolePermissionRemoveList);
        }
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
        return getBaseMapper().listPermissionValuesByUserId(userId);
    }

    @Override
    public List<JSONObject> listPermissionsByUserId(Long userId) {
        List<Permission> selectPermissionList;
        selectPermissionList = getBaseMapper().listPermissionsByUserId(userId);

        List<Permission> permissionList =
            listPermissionsReverse(getBaseMapper().getRootPermission(), selectPermissionList);
        return buildRouterPermissions(permissionList);
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

    /** 递归构建权限树形结构 */
    public List<Permission> listPermissionsReverse(Permission root, List<Permission> nodes) {
        List<Permission> trees = new ArrayList<>();
        root.setLevel(1);
        trees.add(listChildPermissions(root, nodes));
        return trees;
    }

    /** 构建前端router中的权限结构JSON */
    public List<JSONObject> buildRouterPermissions(List<Permission> nodes) {
        List<JSONObject> menus = new ArrayList<>();
        if (nodes.size() == 1) {
            Permission topNode = nodes.get(0);

            List<Permission> oneMenuList = topNode.getChildren();
            for (Permission one : oneMenuList) {
                buildRouter(menus, one);
                List<Permission> twoMenuList = one.getChildren();
                for (Permission two : twoMenuList) {
                    buildRouter(menus, two);
                    List<Permission> threeMenuList = two.getChildren();
                    for (Permission three : threeMenuList) {
                        buildRouter(menus, three);
                    }
                }
            }
        }
        return menus;
    }

    private void buildRouter(List<JSONObject> menus, Permission permission) {
        JSONObject menu = new JSONObject();
        menu.put("path", permission.getPath());
        menu.put("name", permission.getName());
        JSONObject meta = new JSONObject();
        meta.put("roles", rolePermissionService.listAvailableRoles(permission.getId()));
        menu.put("meta", meta);
        menus.add(menu);
    }

    /** 递归查找子节点 */
    private Permission listChildPermissions(Permission root, List<Permission> nodes) {
        root.setChildren(new ArrayList<>());

        for (Permission node : nodes) {
            if (root.getId().equals(node.getPid())) {
                node.setLevel(root.getLevel() + 1);
                root.getChildren().add(listChildPermissions(node, nodes));
            }
        }
        return root;
    }
}
