package team.lte.aclservice.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

import team.lte.aclservice.entity.po.RolePermission;

/**
 * <p>
 * 角色权限关系表 服务类
 * </p>
 *
 * @author lte
 * @since 2022-04-29
 */
public interface RolePermissionService extends IService<RolePermission> {

    /** 根据permissionId查询具有该权限的所有角色 */
    List<String> listAvailableRoles(Long id);

}
