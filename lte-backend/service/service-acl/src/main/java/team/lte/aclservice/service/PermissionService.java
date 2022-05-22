package team.lte.aclservice.service;

import com.alibaba.fastjson.JSONObject;
import team.lte.aclservice.entity.po.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author lte
 * @since 2022-04-28
 */
public interface PermissionService extends IService<Permission> {

    /** 获取所有权限 */
    List<Permission> listPermissions();

    /** 获取某个职务的所有权限 */
    List<Permission> listPermissionsByRoleId(Long roleId);

    /** 为某个职务分配权限 */
    boolean assignPermissionsByRoleId(Long roleId, List<Long> permissionIds);

    /** 删除权限及其子权限 */
    boolean removePermissionsById(Long id);

    /** 获取某个用户的所有权限值 */
    List<String> listPermissionValuesByUserId(Long userId);

    /** 获取某个用户的所有权限 */
    List<JSONObject> listPermissionsByUserId(Long userId);

}
