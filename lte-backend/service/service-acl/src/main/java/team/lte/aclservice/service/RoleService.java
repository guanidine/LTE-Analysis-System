package team.lte.aclservice.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;

import team.lte.aclservice.entity.po.Role;

/**
 * <p>
 * 职务表 服务类
 * </p>
 *
 * @author lte
 * @since 2022-04-28
 */
public interface RoleService extends IService<Role> {

    Map<String, Object> findRolesByUserId(Long userId);

    boolean assignRolesByUserId(Long userId, List<Long> roleIds);

    List<Role> listRolesByUserId(Long userId);

}
