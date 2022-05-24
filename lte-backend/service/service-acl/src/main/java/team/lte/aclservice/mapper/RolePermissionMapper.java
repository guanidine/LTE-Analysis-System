package team.lte.aclservice.mapper;

import team.lte.aclservice.entity.po.RolePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 职务权限关系表 Mapper 接口
 * </p>
 *
 * @author lte
 * @since 2022-04-29
 */
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

    List<String> listAvailableRoles(Long id);

}
