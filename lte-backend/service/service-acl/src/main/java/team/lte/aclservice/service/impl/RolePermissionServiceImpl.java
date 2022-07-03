package team.lte.aclservice.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import team.lte.aclservice.entity.po.RolePermission;
import team.lte.aclservice.mapper.RolePermissionMapper;
import team.lte.aclservice.service.RolePermissionService;

/**
 * <p>
 * 角色权限关系表 服务实现类
 * </p>
 *
 * @author lte
 * @since 2022-04-29
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission>
    implements RolePermissionService {

    @Override
    public List<String> listAvailableRoles(Long id) {
        return getBaseMapper().listAvailableRoles(id);
    }

}
